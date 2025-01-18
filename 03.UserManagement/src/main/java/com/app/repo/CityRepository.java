package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {

	public List<CityEntity> findByStateStateId(Integer stateId);
}
