package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnquiryFilterDTO {
	
	private String classMode;
	private String courses;
	private String enqStatus;
	
}
