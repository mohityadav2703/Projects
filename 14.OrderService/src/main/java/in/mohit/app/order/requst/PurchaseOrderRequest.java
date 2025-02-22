package in.mohit.app.order.requst;

import java.util.List;

import in.mohit.app.order.dto.AddressDTO;
import in.mohit.app.order.dto.CustomerDTO;
import in.mohit.app.order.dto.OrderDTO;
import in.mohit.app.order.dto.OrderItemDTO;
import lombok.Data;

@Data
public class PurchaseOrderRequest {
	
	private CustomerDTO customer;
	private AddressDTO address;
	private OrderDTO order;
	private List<OrderItemDTO> orderItems;

}
