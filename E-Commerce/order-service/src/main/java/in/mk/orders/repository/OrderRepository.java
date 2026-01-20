package in.mk.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.mk.orders.entity.Order;
import in.mk.orders.entity.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query("update Order o set o.status = :status where o.id = :id")
    void updateStatus(@Param("id") Long id,
                      @Param("status") OrderStatus status);
}
