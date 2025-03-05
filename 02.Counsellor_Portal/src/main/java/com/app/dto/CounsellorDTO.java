package com.app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CounsellorDTO {
	
	private Integer counsellorId;
	private String name;
	private String email;
	private String password;
	private String phNo;
	
}
