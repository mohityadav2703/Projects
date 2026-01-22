package in.mk.inventory.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.common.product.ProductEvent;

import in.mk.inventory.entity.Inventory;
import in.mk.inventory.repository.InventoryRepository;

@Component
public class ProductEventListener {

    @Autowired
    private InventoryRepository repository;

    @KafkaListener(
        topics = "${inventory.kafka.topic}",
        groupId = "inventory-group"
    )
    public void consumeProductEvent(ProductEvent event) {

        if ("PRODUCT_CREATED".equals(event.getEventType())) {

            Inventory inventory = new Inventory();
            inventory.setProductId(event.getProductId());
            inventory.setQuantity(event.getQuantity());

            repository.save(inventory);
        }
    }
}