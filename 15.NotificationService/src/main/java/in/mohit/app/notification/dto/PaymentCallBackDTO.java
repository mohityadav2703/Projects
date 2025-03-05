package in.mohit.app.notification.dto;

import lombok.Data;

@Data
public class PaymentCallBackDTO {
	
	private String razorpayOrderId;
	private String razorpayPaymentId;
	private String razorpaySignature;

}
