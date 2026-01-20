package in.mk.orders.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.OrderEvent;
import com.common.OrderEventType;

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

    // ---------------- UTIL ----------------

    private String currentUser() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }

    // ---------------- SINGLE ORDER ----------------

    @Override
    @Transactional
    public Order placeOrder(Long productId, Integer qty) {

        Order order = repo.save(
                Order.builder()
                        .userEmail(currentUser())
                        .productId(productId)
                        .quantity(qty)
                        .status(OrderStatus.CREATED)
                        .build()
        );

        producer.send(new OrderEvent(
                order.getId(),
                productId,
                qty,
                currentUser(),
                OrderEventType.ORDER_CREATED
        ));

        return order;
    }

    // ---------------- CART CHECKOUT ----------------

    @Override
    @Transactional
    public List<Order> checkout() {

        List<CartItemDto> items = cartClient.myCart();
        List<Order> orders = new ArrayList<>();

        if (items == null || items.isEmpty()) {
            return orders;
        }

        for (CartItemDto item : items) {

            Order order = repo.save(
                    Order.builder()
                            .userEmail(currentUser())
                            .productId(item.getProductId())
                            .quantity(item.getQuantity())
                            .status(OrderStatus.CREATED)
                            .build()
            );

            orders.add(order);

            producer.send(new OrderEvent(
                    order.getId(),
                    item.getProductId(),
                    item.getQuantity(),
                    currentUser(),
                    OrderEventType.ORDER_CREATED
            ));
        }

        return orders;
    }

    // ---------------- STATE TRANSITIONS ----------------

    @Override
    @Transactional
    public void confirm(Long id) {

        Order order = repo.findById(id).orElseThrow();

        // ✅ idempotent protection
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
        transitionById(id, OrderStatus.RETURN_REQUESTED, OrderEventType.ORDER_RETURN_REQUESTED);
    }

    @Override
    @Transactional
    public void markReturned(Long id) {
        transitionById(id, OrderStatus.RETURNED, OrderEventType.ORDER_RETURNED);
    }

    // ---------------- INTERNAL HELPERS ----------------

    private void transitionById(Long id, OrderStatus to, OrderEventType type) {
        Order order = repo.findById(id).orElseThrow();
        transition(order, to, type);
    }

    private void transition(Order order, OrderStatus to, OrderEventType eventType) {

        if (!stateMachine.canMove(order.getStatus(), to)) {
            throw new IllegalStateException(
                    "Invalid transition " + order.getStatus() + " -> " + to
            );
        }

        order.setStatus(to);
        repo.save(order);

        producer.send(new OrderEvent(
                order.getId(),
                order.getProductId(),
                order.getQuantity(),
                order.getUserEmail(),
                eventType
        ));
    }
}
