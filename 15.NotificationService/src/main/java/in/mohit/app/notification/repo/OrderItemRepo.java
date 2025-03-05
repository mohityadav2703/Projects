package in.mohit.app.notification.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mohit.app.notification.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

}
