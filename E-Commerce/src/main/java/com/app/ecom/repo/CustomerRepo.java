package com.app.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecom.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
