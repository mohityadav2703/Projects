package in.mk.cart.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.mk.cart.dto.ProductResponse;

@FeignClient(name = "product-service")
public interface ProductFeignClient {

    @GetMapping("/product/search")
    ProductResponse search(@RequestParam("q") String q);
}