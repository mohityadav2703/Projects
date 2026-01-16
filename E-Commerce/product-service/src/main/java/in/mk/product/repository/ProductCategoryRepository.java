package in.mk.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mk.product.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findByName(String name);
}
