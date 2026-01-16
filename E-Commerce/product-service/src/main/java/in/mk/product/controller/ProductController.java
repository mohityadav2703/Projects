package in.mk.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.mk.product.dto.ProductRequest;
import in.mk.product.dto.ProductResponse;
import in.mk.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/create")
    public ProductResponse create(@RequestBody ProductRequest request) {
        return service.create(request);
    }

    @GetMapping("/all")
    public List<ProductResponse> all() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<ProductResponse> search(@RequestParam("q") String q) {
        return service.search(q);
    }
}
