package com.app.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.customer.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {

}
