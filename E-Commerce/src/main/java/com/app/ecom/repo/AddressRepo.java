package com.app.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecom.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{

}
