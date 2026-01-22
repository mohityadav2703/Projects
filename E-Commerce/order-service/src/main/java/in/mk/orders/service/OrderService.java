package in.mk.orders.service;

import java.util.List;

import in.mk.orders.dto.CartCheckoutItemDto;
import in.mk.orders.entity.Order;

public interface OrderService {

    // ---------------- PLACE ORDER ----------------
    Order placeOrder(Long productId, Integer qty);
    List<Order> checkout();
    
    List<Order> checkoutPartial(List<CartCheckoutItemDto> items);
    // ---------------- STATE CHANGES ----------------
    void confirm(Long orderId);

    void deliver(Long orderId);

    void cancel(Long orderId);

    // ---------------- RETURN FLOW ----------------
    void requestReturn(Long orderId);

    void markReturned(Long orderId);
}
