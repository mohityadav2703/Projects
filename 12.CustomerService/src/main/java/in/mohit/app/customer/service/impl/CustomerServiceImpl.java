package in.mohit.app.customer.service.impl;

import java.util.Random;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.mohit.app.customer.dto.CustomerDTO;
import in.mohit.app.customer.dto.ResetPwdDTO;
import in.mohit.app.customer.entity.Customer;
import in.mohit.app.customer.mapper.CustomerMapper;
import in.mohit.app.customer.repo.CustomerRepo;
import in.mohit.app.customer.response.AuthResponse;
import in.mohit.app.customer.service.CustomerService;
import in.mohit.app.customer.service.EmailService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepo customerRepo;
	private BCryptPasswordEncoder pwdEncoder;
	private AuthenticationManager authManager;
	private EmailService emailService;

	public CustomerServiceImpl(CustomerRepo customerRepo, BCryptPasswordEncoder pwdEncoder,
			AuthenticationManager authManager, EmailService emailService) {
		this.customerRepo = customerRepo;
		this.pwdEncoder = pwdEncoder;
		this.authManager = authManager;
		this.emailService = emailService;
	}

	Random rnd = new Random();

	// CHECK EMAIL-UNIQUE
	@Override
	public Boolean isEmailUnique(String email) {
		Customer customer = customerRepo.findByEmail(email);
		if (customer == null) {
			return true;
		}
		return false;
	}

	// REGISTER-CUSTOMER
	@Override
	public Boolean registerCustomer(CustomerDTO customerDto) {

		String randomPwd = getRandomPwd();
		String encodedPwd = pwdEncoder.encode(randomPwd);

		customerDto.setPassword(encodedPwd);
		customerDto.setUpdatedPassword("NO");

		Customer customerEntity = CustomerMapper.convertToEntity(customerDto);
		Customer savedCustomer = customerRepo.save(customerEntity);

		if (savedCustomer.getId() != null) {
			String subject = "Registration Success";
			String body = "Your Login Password :" + randomPwd;
			return emailService.sendEmail(customerDto.getEmail(), subject, body);
		}
		return false;
	}

	// CUSTOMER-LOGIN
	@Override
	public AuthResponse login(CustomerDTO customerDto) {

		AuthResponse response = null;

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customerDto.getEmail(),
				customerDto.getPassword());
		Authentication authenticate = authManager.authenticate(token);

		if (authenticate.isAuthenticated()) {
			Customer customer = customerRepo.findByEmail(customerDto.getEmail());
			response = new AuthResponse();
			response.setCustomer(CustomerMapper.convertToDTO(customer));
			response.setToken("");
		}
		return response;
	}

	// FIND-CUSTOMER
	@Override
	public CustomerDTO getCustomerByEmail(String email) {

		Customer customer = customerRepo.findByEmail(email);
		if (customer != null) {
			return CustomerMapper.convertToDTO(customer);
		}
		return null;
	}

	// UPDATE-PASSWORD
	@Override
	public Boolean resetPwd(ResetPwdDTO resetPwdDto) {
		Customer customerDetails = customerRepo.findByEmail(resetPwdDto.getEmail());

		if (customerDetails != null) {
			String newPwd = resetPwdDto.getNewPwd();
			String encodedPwd = pwdEncoder.encode(newPwd);
			customerDetails.setPassword(encodedPwd);
			customerDetails.setUpdatedPassword("YES");
			customerRepo.save(customerDetails);
			return true;
		}

		return false;
	}

	// FORGOT-PASSWORD
	@Override
	public Boolean forgotPwd(String email) {

		Customer customer = customerRepo.findByEmail(email);
		if (customer != null) {
			String subject = "Password Update Request";
			String body = "temp body";
			emailService.sendEmail(email, subject, body);
			return true;
		}
		return false;
	}

	// GENERATE RANDOM PASSWORD
	private String getRandomPwd() {
		String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

		StringBuilder pwd = new StringBuilder();

		while (pwd.length() < 5) { // length of the random string.
			int index = rnd.nextInt(saltChars.length());
			pwd.append(saltChars.charAt(index));
		}
		return pwd.toString();
	}
}
