package in.mk.orders.service;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import in.mk.orders.entity.OrderStatus;

@Component
public class OrderStateMachine {

    private static final Map<OrderStatus, Set<OrderStatus>> FLOW = Map.of(

        OrderStatus.CREATED,
            Set.of(
                OrderStatus.CONFIRMED,
                OrderStatus.CANCELLED
            ),

        OrderStatus.CONFIRMED,
            Set.of(
                OrderStatus.DELIVERED,
                OrderStatus.CANCELLED,
                OrderStatus.RETURN_REQUESTED   // ✅ FIX ADDED
            ),

        OrderStatus.DELIVERED,
            Set.of(
                OrderStatus.RETURN_REQUESTED
            ),

        OrderStatus.RETURN_REQUESTED,
            Set.of(
                OrderStatus.RETURNED
            )
    );

    public boolean canMove(OrderStatus from, OrderStatus to) {
        return FLOW.getOrDefault(from, Set.of()).contains(to);
    }
}
