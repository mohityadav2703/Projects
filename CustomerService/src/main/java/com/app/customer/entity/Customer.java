package com.app.customer.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long custId;

	@Column(name = "name")
	private String custName;

	private String email;

	@Column(name = "password")
	private String pwd;

	private String pwdUpdated;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.DATE) // it use to specify in which formate you want to insert date
	@Column(name = "UPDATED_DATE", insertable = false)
	private Date updatedDate;


}
