package com.app.customer.dto;

import lombok.Data;

@Data
public class CustomerDTO {
	
	private Long custId;
	private String custName;
	private String email;
	private String pwd;
	private String pwdUpdated;

}
