package in.mohit.maxbi.dto;

import lombok.Data;

@Data
public class OrderItemDTO {

	  private Long id;
	    private ProductDTO product;
	    private Integer quantity;
	    private Double price;
}
