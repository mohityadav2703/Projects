package in.mk.product.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void productCreated(String productName) {
        kafkaTemplate.send("product-events", "CREATED: " + productName);
    }
}
