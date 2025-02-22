package com.app.ecom.dto;

import lombok.Data;

@Data
public class OrderItemsDTO {

	private Long orderItemId;
	private String imgUrl;
	private Integer unitPrice;
	private Integer quantity;
}
