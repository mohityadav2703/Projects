package in.mk.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mk.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String keyword);
}
