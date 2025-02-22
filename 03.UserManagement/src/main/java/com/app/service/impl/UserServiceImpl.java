package com.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.dto.QuoteResponseDTO;
import com.app.dto.ResetPwdDTO;
import com.app.dto.UserDTO;
import com.app.entity.CityEntity;
import com.app.entity.CountryEntity;
import com.app.entity.StateEntity;
import com.app.entity.UserEntity;
import com.app.repo.CityRepository;
import com.app.repo.CountryRepository;
import com.app.repo.StateRepository;
import com.app.repo.UserRepository;
import com.app.service.EmailService;
import com.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private EmailService emailService;

	@Override
	public UserDTO login(String email, String pwd) {

		UserEntity entity = userRepo.findByEmailAndPwd(email, pwd);

		if (entity != null) {
			UserDTO dto = new UserDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}

		return null;
	}

	@Override
	public Map<Integer, String> getCountries() {

		List<CountryEntity> list = countryRepo.findAll();

		Map<Integer, String> countryMap = new HashMap<>();

		list.forEach(country -> {
			countryMap.put(country.getCountryId(), country.getCountryName());
		});

		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {

		List<StateEntity> list = stateRepo.findByCountryCountryId(countryId);

		Map<Integer, String> statesMap = new HashMap<>();

		list.forEach(state -> {
			statesMap.put(state.getStateId(), state.getStateName());
		});

		return statesMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {

		List<CityEntity> list = cityRepo.findByStateStateId(stateId);

		Map<Integer, String> cityMap = new HashMap<>();

		list.forEach(city -> 
			cityMap.put(city.getCityId(), city.getCityName()));

		return cityMap;
	}

	@Override
	public boolean isEmailUnique(String email) {
		return null == userRepo.findByEmail(email);
	}

	@Override
	public boolean register(UserDTO userDto) {

		String randomPwd = getRandomPwd();
		userDto.setPwd(randomPwd);
		userDto.setPwdUpdated("NO");

		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(userDto, entity);

		CountryEntity country = countryRepo.findById(userDto.getCountryId()).get();
		entity.setCountry(country);

		StateEntity state = stateRepo.findById(userDto.getStateId()).get();
		entity.setState(state);

		CityEntity city = cityRepo.findById(userDto.getCityId()).get();
		entity.setCity(city);

		UserEntity savedUser = userRepo.save(entity);

		if (savedUser != null) {
			String subject = "Your Registration Success";
			String body = "Your Account Login Pwd : " + randomPwd;
			return emailService.sendEmail(userDto.getEmail(), subject, body);
		}

		return false;
	}

	@Override
	public boolean resetPwd(ResetPwdDTO resetPwdDto) {

		UserEntity entity = userRepo.findByEmail(resetPwdDto.getEmail());

		entity.setPwd(resetPwdDto.getNewPwd());
		entity.setPwdUpdated("YES");

		UserEntity save = userRepo.save(entity);

		return save != null;
	}

	@Override
	public QuoteResponseDTO getQuotation() {

		String apiUrl = "https://dummyjson.com/quotes/random";

		RestTemplate rt = new RestTemplate();

		ResponseEntity<QuoteResponseDTO> forEntity = rt.getForEntity(apiUrl, QuoteResponseDTO.class);

		return forEntity.getBody();
	}

	private String getRandomPwd() {

		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

		StringBuilder pwd = new StringBuilder();

		Random rnd = new Random();

		while (pwd.length() < 5) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			pwd.append(SALTCHARS.charAt(index));
		}

		return pwd.toString();
	}

}
