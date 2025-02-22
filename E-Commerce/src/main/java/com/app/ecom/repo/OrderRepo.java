package com.app.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecom.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long>{

}
