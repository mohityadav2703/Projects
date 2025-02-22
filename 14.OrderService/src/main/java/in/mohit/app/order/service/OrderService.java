package in.mohit.app.order.service;

import java.util.List;

import in.mohit.app.order.dto.OrderDTO;
import in.mohit.app.order.dto.PaymentCallBackDTO;
import in.mohit.app.order.requst.PurchaseOrderRequest;
import in.mohit.app.order.response.PurchaseOrderResponse;

public interface OrderService {
	
	public PurchaseOrderResponse createOrder(PurchaseOrderRequest orderRequest);
	public PurchaseOrderResponse updateOrder(PaymentCallBackDTO paymentCallBackDto);
	public List<OrderDTO> getOrderByEmail(String email);

}
