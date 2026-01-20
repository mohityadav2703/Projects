package in.mk.inventory.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.common.OrderEvent;
import com.common.OrderEventType;

import in.mk.inventory.entity.Inventory;
import in.mk.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InventoryOrderConsumer {

    private final InventoryRepository repo;
    private final KafkaTemplate<String, OrderEvent> kafka;

    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void consume(OrderEvent event) {

        switch (event.getType()) {

            case ORDER_CREATED -> reserve(event);
            case ORDER_CANCELLED, ORDER_RETURNED -> restore(event);
            default -> { }
        }
    }

    private void reserve(OrderEvent event) {

        Inventory inv = repo.findByProductId(event.getProductId()).orElseThrow();
        inv.setQuantity(inv.getQuantity() - event.getQuantity());
        repo.save(inv);

        kafka.send("inventory-events",
                new OrderEvent(
                        event.getOrderId(),
                        event.getProductId(),
                        event.getQuantity(),
                        event.getUserEmail(),
                        OrderEventType.INVENTORY_RESERVED
                ));
    }

    private void restore(OrderEvent event) {

        Inventory inv = repo.findByProductId(event.getProductId()).orElseThrow();
        inv.setQuantity(inv.getQuantity() + event.getQuantity());
        repo.save(inv);

        kafka.send("inventory-events",
                new OrderEvent(
                        event.getOrderId(),
                        event.getProductId(),
                        event.getQuantity(),
                        event.getUserEmail(),
                        OrderEventType.INVENTORY_RESTORED
                ));
    }
}
