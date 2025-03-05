package com.app.service.impl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.CounsellorDTO;
import com.app.entity.CounsellorEntity;
import com.app.repo.CounsellorRepository;
import com.app.service.CounsellorService;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	private CounsellorRepository counsellorRepo;

	@Override
	public CounsellorDTO login(CounsellorDTO counsellorDto) {
		CounsellorEntity entity = counsellorRepo.findByEmailAndPassword(counsellorDto.getEmail(),
				counsellorDto.getPassword());
		if (entity != null) {
			CounsellorDTO dto = new CounsellorDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return null;
	}

	@Override
	public boolean uniqueEmailCheck(String email) {
		CounsellorEntity entity = counsellorRepo.findByEmail(email);
		if (entity == null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean register(CounsellorDTO counsellorDto) {
		CounsellorEntity entity = new CounsellorEntity();
		BeanUtils.copyProperties(counsellorDto, entity);
		CounsellorEntity savedEntity = counsellorRepo.save(entity);
		return null != savedEntity.getCounsellorId();
	}

	@Override
	public CounsellorEntity findById(Integer counsellorId) {
		return counsellorRepo.findById(counsellorId).orElse(null);
	}

}
