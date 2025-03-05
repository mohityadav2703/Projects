package in.mohit.app.order.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.mohit.app.order.dto.OrderDTO;
import in.mohit.app.order.dto.PaymentCallBackDTO;
import in.mohit.app.order.requst.PurchaseOrderRequest;
import in.mohit.app.order.response.ApiResponse;
import in.mohit.app.order.response.PurchaseOrderResponse;
import in.mohit.app.order.service.OrderService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse<PurchaseOrderResponse>> createOrder(@RequestBody PurchaseOrderRequest orderRequest){
		
		ApiResponse<PurchaseOrderResponse> response = new ApiResponse<>();
		
		PurchaseOrderResponse orderResponse= orderService.createOrder(orderRequest);
		if(orderResponse!=null) {
			response.setStatus(200);
			response.setMessage("ORDER_CREATED");
			response.setData(orderResponse);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			response.setStatus(500);
			response.setMessage("ORDER_CREATION_FAILED");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<ApiResponse<PurchaseOrderResponse>> updateOrder(@RequestBody PaymentCallBackDTO callbackDto){
		
		ApiResponse<PurchaseOrderResponse> response = new ApiResponse<>();
		
		PurchaseOrderResponse orderResponse= orderService.updateOrder(callbackDto);
		if(orderResponse!=null) {
			response.setStatus(200);
			response.setMessage("ORDER_UPDATED");
			response.setData(orderResponse);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			response.setStatus(500);
			response.setMessage("ORDER_UPDATION_FAILED");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/allOrders/{email}")
	public ResponseEntity<ApiResponse<List<OrderDTO>>> getAllOrders(@PathVariable(name="email") String email){
		
		ApiResponse<List<OrderDTO>> response = new ApiResponse<>();
		
		List<OrderDTO> listOfOrders= orderService.getOrderByEmail(email);
		if(listOfOrders!=null) {
			response.setStatus(200);
			response.setMessage("ORDER_FETCHED_SUCCESSFULL");
			response.setData(listOfOrders);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			response.setStatus(500);
			response.setMessage("FAILED_TO_FETCH_ORDERS");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
