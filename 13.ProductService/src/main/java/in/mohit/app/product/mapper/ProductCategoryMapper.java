package in.mohit.app.product.mapper;

import org.modelmapper.ModelMapper;

import in.mohit.app.product.dto.ProductCategoryDTO;
import in.mohit.app.product.entity.ProductCategory;

public class ProductCategoryMapper {
	
private static final ModelMapper modelMapper=new ModelMapper();
	
	
	public static ProductCategoryDTO convertToDTO(ProductCategory entity) {
		return modelMapper.map(entity, ProductCategoryDTO.class);
	}
	
	public static ProductCategory convertToEntity(ProductCategoryDTO productCategoryDto) {
		return modelMapper.map(productCategoryDto, ProductCategory.class);
	}

}
