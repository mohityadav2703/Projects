package com.app.service;

import java.util.List;

import com.app.dto.DashboardResponseDTO;
import com.app.dto.EnquiryDTO;
import com.app.dto.EnquiryFilterDTO;

public interface EnquiryService {
	
	public DashboardResponseDTO getDashboardInfo(Integer courseId);
	public boolean addEnquiry(EnquiryDTO enqDto, Integer counsellorId);
	public List<EnquiryDTO> getEnquiries(Integer counsellorId);
	public List<EnquiryDTO> getEnquiries(EnquiryFilterDTO enqFilterDto, Integer counsellorId);
	public EnquiryDTO getEnquiryById(Integer enqId);
}
