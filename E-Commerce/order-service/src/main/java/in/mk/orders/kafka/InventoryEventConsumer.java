package in.mk.orders.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.common.OrderEvent;

import in.mk.orders.service.OrderService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InventoryEventConsumer {

    private final OrderService service;

    @KafkaListener(topics = "inventory-events", groupId = "order-group")
    public void consume(OrderEvent event) {

        switch (event.getType()) {

            case INVENTORY_RESERVED ->
                service.confirm(event.getOrderId());

            case INVENTORY_RESTORED ->
                service.markReturned(event.getOrderId());

            default -> {}
        }
    }
}

