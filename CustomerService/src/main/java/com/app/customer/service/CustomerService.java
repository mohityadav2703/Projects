package com.app.customer.service;

import com.app.customer.dto.CustomerDTO;
import com.app.customer.dto.ResetPwdDTO;

public interface CustomerService {

	public boolean registerCustomer(CustomerDTO custDto);
	
	public String login(String email, String pwd);
	
	public String resetPwd(ResetPwdDTO resetPwdDto);
	
	public String forgotPassword(String email);
}
