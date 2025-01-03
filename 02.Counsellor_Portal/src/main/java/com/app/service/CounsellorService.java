package com.app.service;

import com.app.dto.CounsellorDTO;
import com.app.entity.CounsellorEntity;

public interface CounsellorService {
	
	public CounsellorDTO login(CounsellorDTO counsellorDto);
	public boolean uniqueEmailCheck(String email);
	public boolean register(CounsellorDTO counsellorDto);
	public CounsellorEntity findById(Integer counsellorId);

}
