package in.mk.cart.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.common.order.*;

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

        if (event.getType() != OrderEventType.ORDER_CREATED) {
            return;
        }

        // 🔥 PARTIAL checkout → reduce only ordered quantity
        if (event.getContext() != null &&
            event.getContext().getCheckoutType() == CheckoutType.PARTIAL) {

            cartService.removeItem(
                event.getUserEmail(),
                event.getProductId(),
                event.getQuantity()
            );
            return;
        }

        // 🔥 FULL checkout → clear cart
        if (event.getContext() != null &&
            event.getContext().getCheckoutType() == CheckoutType.FULL) {

            cartService.clearByUser(event.getUserEmail());
        }
    }
}
