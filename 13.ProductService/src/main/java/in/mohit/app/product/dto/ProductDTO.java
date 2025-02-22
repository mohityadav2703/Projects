package in.mohit.app.product.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDTO {

	private Long id;
	private String name;
	private String description;
	private String title;
	private Double unitPrice;
	private String imgUrl;
	private String active;
	private Integer unitInStock;
	
	private Date createdDate;
	
}
