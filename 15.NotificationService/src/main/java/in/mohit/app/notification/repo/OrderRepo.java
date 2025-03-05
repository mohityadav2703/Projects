package in.mohit.app.notification.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mohit.app.notification.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
	
	public Order findByRazorpayOrderId(String razorPayOrderId);
	
	public List<Order> findByEmail(String email);

}
