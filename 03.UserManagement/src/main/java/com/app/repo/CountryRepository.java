package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {

}
