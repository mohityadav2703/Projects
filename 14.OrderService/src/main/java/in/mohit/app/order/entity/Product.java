package in.mohit.app.order.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	private String title;
	private Double unitPrice;
	private String imgUrl;
	private String active;
	private Integer unitInStock;

	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private ProductCategory category;
}
