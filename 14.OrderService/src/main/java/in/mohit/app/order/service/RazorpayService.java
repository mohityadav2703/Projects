package in.mohit.app.order.service;

import com.razorpay.Order;

public interface RazorpayService {
	
	public Order createPaymentOrder(Double amount);

}
