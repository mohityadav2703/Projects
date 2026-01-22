package in.mk.orders.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.order.*;

import in.mk.orders.dto.CartCheckoutItemDto;
import in.mk.orders.dto.CartItemDto;
import in.mk.orders.entity.Order;
import in.mk.orders.entity.OrderStatus;
import in.mk.orders.feign.CartClient;
import in.mk.orders.kafka.OrderEventProducer;
import in.mk.orders.repository.OrderRepository;
import in.mk.orders.service.OrderService;
import in.mk.orders.service.OrderStateMachine;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repo;
    private final OrderStateMachine stateMachine;
    private final OrderEventProducer producer;
    private final CartClient cartClient;

    // --------------------------------------------------
    // UTIL
    // --------------------------------------------------

    private String currentUser() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }

    // --------------------------------------------------
    // SINGLE PRODUCT ORDER
    // --------------------------------------------------

    @Override
    @Transactional
    public Order placeOrder(Long productId, Integer qty) {

        Order order = createOrder(productId, qty);

        producer.send(
            OrderEvent.builder()
                .orderId(order.getId())
                .productId(productId)
                .quantity(qty)
                .userEmail(currentUser())
                .type(OrderEventType.ORDER_CREATED)
                // No context → backward compatible
                .build()
        );

        return order;
    }

    // --------------------------------------------------
    // FULL CART CHECKOUT
    // --------------------------------------------------

    @Override
    @Transactional
    public List<Order> checkout() {

        List<CartItemDto> cart = cartClient.myCart();
        List<Order> orders = new ArrayList<>();

        if (cart == null || cart.isEmpty()) {
            return orders;
        }

        for (CartItemDto item : cart) {

            Order order = createOrder(
                item.getProductId(),
                item.getQuantity()
            );

            orders.add(order);

            producer.send(
                OrderEvent.builder()
                    .orderId(order.getId())
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .userEmail(currentUser())
                    .type(OrderEventType.ORDER_CREATED)
                    .context(new OrderContext(CheckoutType.FULL))
                    .build()
            );
        }

        return orders;
    }

    // --------------------------------------------------
    // PARTIAL CART CHECKOUT (FIXED)
    // --------------------------------------------------

    @Override
    @Transactional
    public List<Order> checkoutPartial(List<CartCheckoutItemDto> items) {

        if (items == null || items.isEmpty()) {
            return List.of();
        }

        // 1️⃣ Fetch cart
        Map<Long, Integer> cartMap =
            cartClient.myCart().stream()
                .collect(Collectors.toMap(
                    CartItemDto::getProductId,
                    CartItemDto::getQuantity
                ));

        List<Order> orders = new ArrayList<>();

        // 2️⃣ Validate + create orders
        for (CartCheckoutItemDto item : items) {

            Integer available = cartMap.get(item.getProductId());

            if (available == null || available < item.getQuantity()) {
                throw new IllegalStateException(
                    "Insufficient quantity for productId=" + item.getProductId()
                );
            }

            Order order = createOrder(
                item.getProductId(),
                item.getQuantity()
            );

            orders.add(order);

            producer.send(
                OrderEvent.builder()
                    .orderId(order.getId())
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .userEmail(currentUser())
                    .type(OrderEventType.ORDER_CREATED)
                    .context(new OrderContext(CheckoutType.PARTIAL))
                    .build()
            );
        }

        return orders;
    }

    // --------------------------------------------------
    // ORDER STATE TRANSITIONS
    // --------------------------------------------------

    @Override
    @Transactional
    public void confirm(Long id) {

        Order order = repo.findById(id).orElseThrow();

        // idempotent
        if (order.getStatus() == OrderStatus.CONFIRMED) {
            return;
        }

        transition(order, OrderStatus.CONFIRMED, OrderEventType.ORDER_CONFIRMED);
    }

    @Override
    @Transactional
    public void deliver(Long id) {
        transitionById(id, OrderStatus.DELIVERED, OrderEventType.ORDER_DELIVERED);
    }

    @Override
    @Transactional
    public void cancel(Long id) {
        transitionById(id, OrderStatus.CANCELLED, OrderEventType.ORDER_CANCELLED);
    }

    @Override
    @Transactional
    public void requestReturn(Long id) {
        transitionById(id, OrderStatus.RETURN_REQUESTED,
                       OrderEventType.ORDER_RETURN_REQUESTED);
    }

    @Override
    @Transactional
    public void markReturned(Long id) {
        transitionById(id, OrderStatus.RETURNED,
                       OrderEventType.ORDER_RETURNED);
    }

    // --------------------------------------------------
    // INTERNAL HELPERS
    // --------------------------------------------------

    private Order createOrder(Long productId, int qty) {
        return repo.save(
            Order.builder()
                .userEmail(currentUser())
                .productId(productId)
                .quantity(qty)
                .status(OrderStatus.CREATED)
                .build()
        );
    }

    private void transitionById(
            Long id,
            OrderStatus to,
            OrderEventType type) {

        Order order = repo.findById(id).orElseThrow();
        transition(order, to, type);
    }

    private void transition(
            Order order,
            OrderStatus to,
            OrderEventType eventType) {

        if (!stateMachine.canMove(order.getStatus(), to)) {
            throw new IllegalStateException(
                "Invalid transition " +
                order.getStatus() + " -> " + to
            );
        }

        order.setStatus(to);
        repo.save(order);

        producer.send(
            OrderEvent.builder()
                .orderId(order.getId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .userEmail(order.getUserEmail())
                .type(eventType)
                .build()
        );
    }
}
