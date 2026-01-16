package in.mk.inventory.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.common.OrderEvent;

import in.mk.inventory.entity.Inventory;
import in.mk.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InventoryOrderConsumer {

    private final InventoryRepository repository;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @KafkaListener(
        topics = "order-events",
        groupId = "inventory-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(OrderEvent event) {

        if ("ORDER_CREATED".equals(event.getStatus())) {
            reserve(event);
        }

        if ("ORDER_RETURN_REQUESTED".equals(event.getStatus())) {
            restore(event);
        }
    }

    private void reserve(OrderEvent event) {

        Inventory inv = repository.findByProductId(event.getProductId()).orElseThrow();

        if (inv.getQuantity() >= event.getQuantity()) {
            inv.setQuantity(inv.getQuantity() - event.getQuantity());
            repository.save(inv);

            kafkaTemplate.send("inventory-events",
                    new OrderEvent(
                            event.getOrderId(),
                            event.getProductId(),
                            event.getQuantity(),
                            "INVENTORY_RESERVED"
                    ));
        }
    }

    private void restore(OrderEvent event) {

        Inventory inv = repository.findByProductId(event.getProductId()).orElseThrow();

        inv.setQuantity(inv.getQuantity() + event.getQuantity());
        repository.save(inv);

        kafkaTemplate.send("inventory-events",
                new OrderEvent(
                        event.getOrderId(),
                        event.getProductId(),
                        event.getQuantity(),
                        "INVENTORY_RESTORED"
                ));
    }
}
