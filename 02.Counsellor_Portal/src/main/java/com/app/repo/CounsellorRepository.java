package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.CounsellorEntity;

public interface CounsellorRepository extends JpaRepository<CounsellorEntity, Integer> {

	public CounsellorEntity findByEmailAndPassword(String email, String password);
	public CounsellorEntity findByEmail(String email);

}
