package in.mohit.app.customer.service;

import in.mohit.app.customer.dto.CustomerDTO;
import in.mohit.app.customer.dto.ResetPwdDTO;
import in.mohit.app.customer.response.AuthResponse;

public interface CustomerService {
	
	public Boolean isEmailUnique(String email);
	public Boolean registerCustomer(CustomerDTO customerDto);
	
	public CustomerDTO getCustomerByEmail(String email);
	public Boolean resetPwd(ResetPwdDTO resetPwdDto);
	public Boolean forgotPwd(String email);

	public AuthResponse login(CustomerDTO customerDto);
}
