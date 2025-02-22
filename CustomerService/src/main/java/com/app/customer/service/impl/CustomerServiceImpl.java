package com.app.customer.service.impl;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.app.customer.dto.CustomerDTO;
import com.app.customer.dto.ResetPwdDTO;
import com.app.customer.entity.Customer;
import com.app.customer.repo.CustomerRepo;
import com.app.customer.service.CustomerService;
import com.app.customer.service.EmailService;

@Service
public class CustomerServiceImpl implements CustomerService {

	Random rnd = new Random();

	private CustomerRepo custRepo;
	private EmailService emailService;
	
	
	public CustomerServiceImpl(CustomerRepo custRepo, EmailService emailService) {
		this.custRepo=custRepo;
		this.emailService=emailService;
	}

	
	//REGISTRATION
	@Override
	public boolean registerCustomer(CustomerDTO custDto) {

		// check email is unique or not
		Customer customer = custRepo.findByEmail(custDto.getEmail());
		if (customer != null) {
			throw new RuntimeException("Email Already Used , Try With Another Email");
		} else {

			String randomPwd = getRandomPwd();
			custDto.setPwd(randomPwd);
			custDto.setPwdUpdated("NO");

			Customer entity = new Customer();
			BeanUtils.copyProperties(custDto, entity);
			Customer savedCustomer = custRepo.save(entity);

			if (savedCustomer != null) {
				String subject = "Your Registration is Success";
				String body = "Your One Time Login Pwd is :" + randomPwd;
				return emailService.sendEmail(custDto.getEmail(), subject, body);
			}
			return false;
		}
	}

	//LOGIN
	@Override
	public String login(String email, String pwd) {
		Customer custEntity = custRepo.findByEmailAndPwd(email, pwd);
		if (custEntity != null) {
			if (custEntity.getPwdUpdated().equals("NO")) {
				return "This is your first login, please updated the pwd";
			}
			return "LOGIN_SUCCESSFULLY";
		}
		return "Invalid Credentials";
	}


	//UPDATE PASSWORD
	@Override
	public String resetPwd(ResetPwdDTO resetPwdDto) {
		Customer customer = custRepo.findByEmail(resetPwdDto.getEmail());

		if (!customer.getPwd().equals(resetPwdDto.getOldPwd())) {
			return "Old Password does not match";
		}

		if (!resetPwdDto.getNewPwd().equals(resetPwdDto.getConfirmPwd())) {
			return "New Password and Confirm Password do not match";
		}
		customer.setPwd(resetPwdDto.getNewPwd());
		customer.setPwdUpdated("YES");
		custRepo.save(customer);

		return "PASSWORD_UPDATED_SUCCESSFULLY";
	}

	//FORGOT PASSWORD
	@Override
	public String forgotPassword(String email) {
		Customer custData = custRepo.findByEmail(email);
		if (custData != null) {

			// generate random password
			String newPwd = getRandomPwd();
			custData.setPwd(newPwd);
			custData.setPwdUpdated("NO");
			custRepo.save(custData);
			
			// send new password via email
			emailService.sendEmail(custData.getEmail(), "Password Reset", "Your New Password: " + newPwd);
			return "A new Password has been sent to your email.";
			
		} else {
			return "Customer Record Not Found";
		}
	}
	
	
	//GENERATE RANDOM PASSWORD
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
