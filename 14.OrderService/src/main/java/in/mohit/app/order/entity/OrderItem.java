package in.mohit.app.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Order_Items_Data")
public class OrderItem {

	
	private Long id;
	private Integer quantity;
	private Double unitPrice;
	private String imageUrl;
	
	@ManyToOne
	@JoinTable(name="order_id")
	private Order order;
}
