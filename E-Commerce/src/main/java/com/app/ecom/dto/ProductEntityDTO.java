package com.app.ecom.dto;

import lombok.Data;

@Data
public class ProductEntityDTO {

	private Long prodId;
	private String prodName;
	private String description;
	private String title;
	private Double unitPrice;
	private String imgUrl;
	private String active;

}
