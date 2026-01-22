package in.mk.orders.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import in.mk.orders.dto.CartCheckoutItemDto;
import in.mk.orders.dto.CartItemDto;

@FeignClient(name="cart-service")
public interface CartClient {

    @GetMapping("/cart/view")
    List<CartItemDto> myCart();

    @PostMapping("/cart/remove")
    void removeItems(@RequestBody List<CartCheckoutItemDto> items);

    @DeleteMapping("/cart/clear")
    void clearCart();
}
