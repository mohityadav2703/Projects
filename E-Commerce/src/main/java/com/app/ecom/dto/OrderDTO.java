package com.app.ecom.dto;

import lombok.Data;

@Data
public class OrderDTO {

	private Long orderId;

	private String orderTrackingNumber;
	private Integer totalQty;
	private Double totalPrice;
	private String orderStatus;
	
	private String paymentStatus;
	private String razorPayOrderId;
	private String razorPayPaymentId;

}
