package in.mohit.maxbi.dto;

import lombok.Data;

@Data
public class CartItemDTO {

	private Long id;
	private ProductDTO product;
	private Integer quantity;
}
