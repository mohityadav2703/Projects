package in.mohit.app.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mohit.app.product.entity.ProductCategory;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {

}
