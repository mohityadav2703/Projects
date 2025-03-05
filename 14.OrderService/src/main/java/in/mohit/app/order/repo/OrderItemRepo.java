package in.mohit.app.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mohit.app.order.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

}
