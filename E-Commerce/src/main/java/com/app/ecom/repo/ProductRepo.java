package com.app.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecom.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
