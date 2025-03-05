package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnquiryDTO {

	private Integer enqId;
	private String stuName;
	private String stuPhno;
	private String classMode;
	private String course;
	private String status;

	
}
