package com.common;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerDto {
	private Long id;
	private String name;
	private String email;
	private String password;
	private String updatedPassword;
	private String phNo;
	private String role;
	private Date createdDate;
	private Date updatedDate;

}
