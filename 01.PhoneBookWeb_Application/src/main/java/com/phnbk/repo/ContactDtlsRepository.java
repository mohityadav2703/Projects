package com.phnbk.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phnbk.entity.ContactEntity;

public interface ContactDtlsRepository extends JpaRepository<ContactEntity, Serializable>{

}
