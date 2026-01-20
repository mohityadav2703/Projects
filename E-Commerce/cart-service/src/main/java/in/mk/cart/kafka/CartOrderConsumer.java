package in.mk.cart.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.common.OrderEvent;
import com.common.OrderEventType;

import in.mk.cart.service.CartService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CartOrderConsumer {

    private final CartService cartService;

    @KafkaListener(
        topics = "order-events",
        groupId = "cart-group"
    )
    public void consume(OrderEvent event) {

        if (event.getType() == OrderEventType.ORDER_CREATED) {
            cartService.clearByUser(event.getUserEmail());
        }
    }
}
