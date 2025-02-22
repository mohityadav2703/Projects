package in.mohit.app.product.mapper;

import org.modelmapper.ModelMapper;

import in.mohit.app.product.dto.ProductDTO;
import in.mohit.app.product.entity.Product;

public class ProductMapper {
	
	private static final ModelMapper modelMapper=new ModelMapper();
	
	
	public static ProductDTO convertToDTO(Product entity) {
		return modelMapper.map(entity, ProductDTO.class);
	}
	
	public static Product convertToEntity(ProductDTO productDto) {
		return modelMapper.map(productDto, Product.class);
	}

}
