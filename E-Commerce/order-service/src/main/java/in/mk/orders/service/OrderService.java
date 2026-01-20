package in.mk.orders.service;

import java.util.List;

import in.mk.orders.entity.Order;

public interface OrderService {

    // ---------------- PLACE ORDER ----------------
    Order placeOrder(Long productId, Integer qty);
    List<Order> checkout();
    // ---------------- STATE CHANGES ----------------
    void confirm(Long orderId);

    void deliver(Long orderId);

    void cancel(Long orderId);

    // ---------------- RETURN FLOW ----------------
    void requestReturn(Long orderId);

    void markReturned(Long orderId);
}
