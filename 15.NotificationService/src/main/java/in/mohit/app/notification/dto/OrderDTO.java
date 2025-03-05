package in.mohit.app.notification.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderDTO {

	private Long orderId;
	private String orderTrackingNum;
	private String razorPayOrderId;	
	private String email;
	private String orderStatus;
	private Double totalPrice;
	private Integer totalQty;
	private String razorPayPaymentId;
	private String invoiceUrl;
	
	private LocalDate deliveryDate;
	private LocalDate createdDate;
	private LocalDate updatedDate;
}
