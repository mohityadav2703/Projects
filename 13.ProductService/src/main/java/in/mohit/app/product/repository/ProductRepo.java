package in.mohit.app.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mohit.app.product.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	
	public List<Product> findByCategoryId(Long categoryId);
	
	public List<Product> findByNameContaining(String name);

}
