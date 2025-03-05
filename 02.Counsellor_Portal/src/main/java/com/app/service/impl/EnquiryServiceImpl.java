package com.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.app.dto.DashboardResponseDTO;
import com.app.dto.EnquiryDTO;
import com.app.dto.EnquiryFilterDTO;
import com.app.entity.CounsellorEntity;
import com.app.entity.EnquiryEntity;
import com.app.repo.CounsellorRepository;
import com.app.repo.EnquiryRepository;
import com.app.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired
	private EnquiryRepository enqRepo;
	
	@Autowired
	private CounsellorRepository counsellorRepo;

	@Override
	public DashboardResponseDTO getDashboardInfo(Integer counsellorId) {
		List<EnquiryEntity> enqsList= enqRepo.findByCounsellorCounsellorId(counsellorId);
		DashboardResponseDTO dto = new DashboardResponseDTO();
		
		int openCnt=enqsList.stream().filter(enq-> enq.getStatus().equals("Open")).collect(Collectors.toList()).size();
		int enrolledCnt=enqsList.stream().filter(enq-> enq.getStatus().equals("Enrolled")).collect(Collectors.toList()).size();
		int lostCnt=enqsList.stream().filter(enq-> enq.getStatus().equals("Lost")).collect(Collectors.toList()).size();
		
		dto.setTotalEnqCnt(enqsList.size());
		dto.setOpenEnqCnt(openCnt);
		dto.setEnrolledEnqCnt(enrolledCnt);
		dto.setLostEnqCnt(lostCnt);
		
		return dto;
	}

	@Override
	public boolean addEnquiry(EnquiryDTO enqDto, Integer counsellorId) {
		
		EnquiryEntity entity = new EnquiryEntity();
		BeanUtils.copyProperties(enqDto, entity);
		
		Optional<CounsellorEntity> byId= counsellorRepo.findById(counsellorId);
		if(byId.isPresent()) {
			CounsellorEntity counsellor= byId.get();
			entity.setCounsellor(counsellor);
		}
		
		EnquiryEntity save= enqRepo.save(entity);
		return save.getEnqId()!=null;
	}

	@Override
	public List<EnquiryDTO> getEnquiries(Integer counsellorId) {
		List<EnquiryEntity> enqList=enqRepo.findByCounsellorCounsellorId(counsellorId);
		List<EnquiryDTO> dtoList = new ArrayList<>();
		
		for(EnquiryEntity entity : enqList) {
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(entity, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<EnquiryDTO> getEnquiries(EnquiryFilterDTO FilterDto, Integer counsellorId) {
		EnquiryEntity entity = new EnquiryEntity();
		
		if(FilterDto.getClassMode()!=null && !FilterDto.getClassMode().equals("")) {
			entity.setClassMode(FilterDto.getClassMode());
		}
		
		if(FilterDto.getCourses()!=null && !FilterDto.getCourses().equals("")) {
			entity.setCourse(FilterDto.getCourses());
		}
		
		if(FilterDto.getEnqStatus()!=null && !FilterDto.getEnqStatus().equals("")) {
			entity.setStatus(FilterDto.getEnqStatus());
		}
		
		CounsellorEntity counsellor = new CounsellorEntity();
		counsellor.setCounsellorId(counsellorId);
		entity.setCounsellor(counsellor);
		
		Example<EnquiryEntity> of = Example.of(entity);
		
		List<EnquiryEntity> enqList=enqRepo.findAll(of);
		
		List<EnquiryDTO> enqsDtoList = new ArrayList<>();
		
		for(EnquiryEntity enq : enqList) {
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(enq, dto);
			enqsDtoList.add(dto);
		}
		return enqsDtoList;
	}

	@Override
	public EnquiryDTO getEnquiryById(Integer enqId) {
		Optional<EnquiryEntity> byId=enqRepo.findById(enqId);
		
		if(byId.isPresent()) {
			EnquiryEntity enquiryEntity= byId.get();
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(enquiryEntity, dto);
			return dto;
		}
		return null;
	}

}
