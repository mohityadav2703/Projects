package com.app.ecom.dto;

import lombok.Data;

@Data
public class CustomerDTO {

	private Long custId;
	private String custName;
	private String custEmail;
	private String pwd;
	private String pwdUpdated;
}
