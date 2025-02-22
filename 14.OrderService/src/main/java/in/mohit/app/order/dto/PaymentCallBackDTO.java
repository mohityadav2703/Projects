package in.mohit.app.order.dto;

import lombok.Data;

@Data
public class PaymentCallBackDTO {
	
	private String razorpayOrderId;
	private String razorpayPaymentId;
	private String razorpaySignature;

}
