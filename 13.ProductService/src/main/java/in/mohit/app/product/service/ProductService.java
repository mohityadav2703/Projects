package in.mohit.app.product.service;

import java.util.List;

import in.mohit.app.product.dto.ProductCategoryDTO;
import in.mohit.app.product.dto.ProductDTO;

public interface ProductService {

	
	public List<ProductCategoryDTO> findAllCategories();
	
	public List<ProductDTO> findProductsByCategoryId(Long categoryId);
	
	public ProductDTO findByProductId(Long id);
	
	public List<ProductDTO> findByProductName(String productName);
}
