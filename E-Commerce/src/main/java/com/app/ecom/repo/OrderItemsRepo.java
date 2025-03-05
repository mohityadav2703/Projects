package com.app.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecom.entity.OrderItems;

public interface OrderItemsRepo extends JpaRepository<OrderItems, Long> {

}
