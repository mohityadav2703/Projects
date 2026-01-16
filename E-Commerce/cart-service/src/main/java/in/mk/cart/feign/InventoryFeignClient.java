package in.mk.cart.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.mk.cart.dto.InventoryResponse;

@FeignClient(name = "inventory-service")
public interface InventoryFeignClient {

    @GetMapping("/inventory/check/{productId}")
    InventoryResponse check(@PathVariable("productId") Long productId);

    @PostMapping("/inventory/reserve")
    void reserve(@RequestParam("productId") Long productId,
                 @RequestParam("qty") int qty);

    @PostMapping("/inventory/release")
    void release(@RequestParam("productId") Long productId,
                 @RequestParam("qty") int qty);
}
