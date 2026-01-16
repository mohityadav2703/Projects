package in.mk.product.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.mk.product.dto.CategoryRequest;
import in.mk.product.dto.CategoryResponse;
import in.mk.product.entity.ProductCategory;
import in.mk.product.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ProductCategoryRepository repository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ProductCategory create(@RequestBody CategoryRequest request) {
        ProductCategory cat = new ProductCategory();
        cat.setName(request.getName());
        return repository.save(cat);
    }

    @GetMapping("/all")
    public List<CategoryResponse> all() {
        return repository.findAll().stream()
            .map(c -> new CategoryResponse(c.getId(), c.getName()))
            .toList();
    }

}

