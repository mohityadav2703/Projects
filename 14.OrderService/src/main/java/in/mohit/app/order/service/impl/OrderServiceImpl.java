package in.mohit.app.order.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.mohit.app.order.dto.AddressDTO;
import in.mohit.app.order.dto.CustomerDTO;
import in.mohit.app.order.dto.OrderDTO;
import in.mohit.app.order.dto.OrderItemDTO;
import in.mohit.app.order.dto.PaymentCallBackDTO;
import in.mohit.app.order.entity.Address;
import in.mohit.app.order.entity.Customer;
import in.mohit.app.order.entity.Order;
import in.mohit.app.order.entity.OrderItem;
import in.mohit.app.order.repo.AddressRepo;
import in.mohit.app.order.repo.CustomerRepo;
import in.mohit.app.order.repo.OrderItemRepo;
import in.mohit.app.order.repo.OrderRepo;
import in.mohit.app.order.requst.PurchaseOrderRequest;
import in.mohit.app.order.response.PurchaseOrderResponse;
import in.mohit.app.order.service.EmailService;
import in.mohit.app.order.service.OrderService;
import in.mohit.app.order.service.RazorpayService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private OrderItemRepo orderItemRepo;

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private RazorpayService razorpayService;
	
	@Autowired
	private EmailService emailService;

	@Override
	public PurchaseOrderResponse createOrder(PurchaseOrderRequest orderRequest) {

		CustomerDTO customerDto = orderRequest.getCustomer();
		AddressDTO addressDto = orderRequest.getAddress();
		OrderDTO orderDto = orderRequest.getOrder();
		List<OrderItemDTO> orderItemsList = orderRequest.getOrderItems();

		// set customer details
		Customer customer = customerRepo.findByEmail(customerDto.getEmail());
		if (customer == null) {
			customer = new Customer();
			customer.setId(customerDto.getId());
			customer.setEmail(customerDto.getEmail());
			customer.setName(customerDto.getName());
			customer.setPhNo(customerDto.getPhNo());
			customerRepo.save(customer);
		}

		// Set Address details
		Address address = new Address();
		address.setHouseNum(addressDto.getHouseNum());
		address.setStreet(addressDto.getStreet());
		address.setCity(addressDto.getCity());
		address.setState(addressDto.getState());
		address.setZipCode(addressDto.getZipCode());
		address.setCustomer(customer);
		addressRepo.save(address);

		Order newOrder = new Order();
		String orderTrackingNumber = generateOrderTrackingNum();
		newOrder.setOrderTrackingNum(orderTrackingNumber);
		newOrder.setTotalPrice(orderDto.getTotalPrice());
		// create razorpay order and get order details
		com.razorpay.Order paymentOrder = razorpayService.createPaymentOrder(newOrder.getTotalPrice());

		newOrder.setRazorpayOrderId(paymentOrder.get("id"));
		newOrder.setOrderStatus(paymentOrder.get("status"));
//		newOrder.setTotalPrice(orderDto.getTotalPrice());
		newOrder.setTotalQty(orderDto.getTotalQty());
		newOrder.setEmail(customer.getEmail());

		newOrder.setCustomer(customer);
		newOrder.setAddress(address);
		orderRepo.save(newOrder);

		// save order items
		for (OrderItemDTO itemDto : orderItemsList) {
			OrderItem item = new OrderItem();
			BeanUtils.copyProperties(itemDto, item);
			item.setOrder(newOrder);
			orderItemRepo.save(item);
		}

		// Prepare and Return Response
		return PurchaseOrderResponse.builder().razorpayOrderId(paymentOrder.get("id"))
				.orderStatus(paymentOrder.get("status")).orderTrackingNumber(orderTrackingNumber).build();

	}

	@Override
	public PurchaseOrderResponse updateOrder(PaymentCallBackDTO paymentCallBackDto) {
		Order order = orderRepo.findByRazorpayOrderId(paymentCallBackDto.getRazorpayOrderId());
		if(order!=null) {
			order.setOrderStatus("CONFIRMED");
			order.setDeliveryDate(LocalDate.now());
			order.setRazorPayPaymentId(paymentCallBackDto.getRazorpayPaymentId());
			orderRepo.save(order);
			
			String subject="Your Order is Confirmed";
			String body="ThankYou, You will receive your order on : "+order.getDeliveryDate(); 
			
			emailService.sendEmail(order.getEmail(), subject, body);
			
		}

		return PurchaseOrderResponse.builder()
							.razorpayOrderId(paymentCallBackDto.getRazorpayOrderId())
							.orderStatus(order.getOrderStatus())
							.orderTrackingNumber(order.getOrderTrackingNum())
							.build();

	}

	@Override
	public List<OrderDTO> getOrderByEmail(String email) {
		List<Order> orderList=orderRepo.findByEmail(email);
		
		List<OrderDTO> listOfDtos= new ArrayList<>();
		
		for(Order order : orderList) {
			OrderDTO dto = new OrderDTO();
			BeanUtils.copyProperties(order, dto);
			listOfDtos.add(dto);
		}
		
		return listOfDtos;
	}

	private String generateOrderTrackingNum() {

		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStamp = simpleDate.format(new Date());
		String randomUuid = UUID.randomUUID().toString().substring(0, 6).toUpperCase();

		return "ORD" + timeStamp + randomUuid;
	}

}
