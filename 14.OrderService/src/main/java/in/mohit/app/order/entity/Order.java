package in.mohit.app.order.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Order_Data")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private String orderTrackingNum;
	private String razorPayOrderId;	//order created
	private String email;
	private String orderStatus;
	private Double totalPrice;
	private Integer totalQty;
	private String razorPayPaymentId; //order confirmed
	private String invoiceUrl;
	
	private LocalDate deliveryDate;
	
	@CreationTimestamp
	private LocalDate createdDate;
	
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	@ManyToOne
	@JoinColumn(name="customer_id")  
	private Customer customer;

	@ManyToOne
	@JoinColumn(name="addr_id")		
	private Address address;
}
