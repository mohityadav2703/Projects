package com.app.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.customer.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
	
	
	Customer findByEmail(String email);
	Customer findByEmailAndPwd(String email, String pwd);

}
