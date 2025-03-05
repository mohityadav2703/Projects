package com.app.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecom.entity.ProductCategory;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {

}
