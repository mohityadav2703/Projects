package in.mk.orders.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import in.mk.orders.dto.CartItemDto;

@FeignClient(name="cart-service")
public interface CartClient {
	
	@GetMapping("/cart/view")
	List<CartItemDto> myCart();

	@DeleteMapping("/cart/clear")
	void clearCart();
}
