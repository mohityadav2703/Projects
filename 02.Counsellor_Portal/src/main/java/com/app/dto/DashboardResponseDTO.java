package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DashboardResponseDTO {
	
	private Integer totalEnqCnt;
	private Integer openEnqCnt;
	private Integer enrolledEnqCnt;
	private Integer lostEnqCnt;

}
