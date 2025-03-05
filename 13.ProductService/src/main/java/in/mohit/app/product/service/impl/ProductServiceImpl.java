package in.mohit.app.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.mohit.app.product.dto.ProductCategoryDTO;
import in.mohit.app.product.dto.ProductDTO;
import in.mohit.app.product.mapper.ProductCategoryMapper;
import in.mohit.app.product.mapper.ProductMapper;
import in.mohit.app.product.repository.ProductCategoryRepo;
import in.mohit.app.product.repository.ProductRepo;
import in.mohit.app.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ProductCategoryRepo categoryRepo;

	@Override
	public List<ProductCategoryDTO> findAllCategories() {
		/*
		List<ProductCategory> categories= categoryRepo.findAll();
		List<ProductCategoryDTO> dtoList=new ArrayList<>();
		
		for(ProductCategory category: categories) {
			ProductCategoryDTO dto = ProductCategoryMapper.convertToDTO(category);
			dtoList.add(dto);
		}
		return dtoList;
	*/
		
		return categoryRepo.findAll()
						   .stream()
						   .map(ProductCategoryMapper::convertToDTO)
						   .collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> findProductsByCategoryId(Long categoryId) {
		
		return productRepo.findByCategoryId(categoryId)
				          .stream()
				          .map(ProductMapper::convertToDTO)
				          .collect(Collectors.toList());
		
	}

	@Override
	public ProductDTO findByProductId(Long id) {
		
		return productRepo.findById(id)
						  .map(ProductMapper::convertToDTO)
						  .orElse(null);
	}

	@Override
	public List<ProductDTO> findByProductName(String productName) {
		
		return productRepo.findByNameContaining(productName)
						  .stream()
						  .map(ProductMapper::convertToDTO)
						  .collect(Collectors.toList());
	}

}
