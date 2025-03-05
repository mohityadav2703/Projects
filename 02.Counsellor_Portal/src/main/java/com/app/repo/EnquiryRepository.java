package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.EnquiryEntity;

public interface EnquiryRepository extends JpaRepository<EnquiryEntity, Integer> {

	public List<EnquiryEntity> findByCounsellorCounsellorId(Integer counsellorId);
}
