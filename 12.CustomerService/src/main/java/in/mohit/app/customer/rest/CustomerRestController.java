package in.mohit.app.customer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.mohit.app.customer.dto.CustomerDTO;
import in.mohit.app.customer.dto.ResetPwdDTO;
import in.mohit.app.customer.response.ApiResponse;
import in.mohit.app.customer.response.AuthResponse;
import in.mohit.app.customer.service.CustomerService;

@RestController
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> register(@RequestBody CustomerDTO customerDto){
		ApiResponse<String> response = new ApiResponse<>();
		
		Boolean emailUnique=customerService.isEmailUnique(customerDto.getEmail());
		if(!emailUnique) {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("DUPLICATE_EMAIL_FOUND");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		Boolean register=customerService.registerCustomer(customerDto);
		if(register) {
			response.setStatus(201);
			response.setMessage("Success");
			response.setData("REGISTRATION_SUCCESS");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}
		else {
			response.setStatus(500);
			response.setMessage("Failed");
			response.setData("REGISTRATION_FAILED");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody CustomerDTO customerDto){
		
		ApiResponse<AuthResponse> response= new ApiResponse<>();
		AuthResponse authResponse= customerService.login(customerDto);
		if(authResponse!=null) {
			response.setStatus(200);
			response.setMessage("LOGIN_SUCCESS");
			response.setData(authResponse);
			return new ResponseEntity<ApiResponse<AuthResponse>>(response, HttpStatus.OK);
		}
		else {
			response.setStatus(400);
			response.setMessage("LOGIN_FAILED");
			response.setData(null);
			return new ResponseEntity<ApiResponse<AuthResponse>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/pwd/reset")
	public ResponseEntity<ApiResponse<String>> updatePassword(@RequestBody ResetPwdDTO resetPwdDto){
		
		ApiResponse<String> response = new ApiResponse<>();
		
		CustomerDTO customerDto=customerService.getCustomerByEmail(resetPwdDto.getEmail());
		if(!pwdEncoder.matches(resetPwdDto.getOldPwd(), customerDto.getPassword())) {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("Current Password is Incorrect");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(!resetPwdDto.getNewPwd().equals(resetPwdDto.getConfirmPwd())) {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("New Password and Confirm Password is Not Match");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		Boolean status=customerService.resetPwd(resetPwdDto);
		if(status) {
			response.setStatus(200);
			response.setMessage("Success");
			response.setData("PASSWORD_UPDATED");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			response.setStatus(500);
			response.setMessage("Failed");
			response.setData("PASSWORD_UPDATE_FAILED");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/pwd/forgot/{email}")
	public ResponseEntity<ApiResponse<String>> forgotPassword(@PathVariable(name="email") String email){
		ApiResponse<String> response= new ApiResponse<>();
		Boolean status=customerService.forgotPwd(email);
		
		if(status) {
			response.setStatus(200);
			response.setMessage("Success");
			response.setData("Email Sent to Reset Pwd");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("No Account Found");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
