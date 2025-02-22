package com.app.customer.dto;

import lombok.Data;

@Data
public class AddressDTO {

	private Long addId;
	private Integer hNo;
	private String street;
	private String city;
	private String state;
	private Long zipCode;
	private String country;
}
