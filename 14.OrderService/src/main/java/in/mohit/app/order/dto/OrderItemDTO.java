package in.mohit.app.order.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
	
	private Long id;
	private Integer quantity;
	private Double unitPrice;
	private String imageUrl;

}
