package com.app.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.customer.dto.CustomerDTO;
import com.app.customer.dto.ResetPwdDTO;
import com.app.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private CustomerService custService;
	
	public CustomerController(CustomerService custService) {
		this.custService=custService;
	}

	@PostMapping("/save")
	public ResponseEntity<String> saveCustomer(@RequestBody CustomerDTO custDto) {
		boolean status=custService.registerCustomer(custDto);
		if(status) {
			return new ResponseEntity<>("Customer Saved", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Customer Not Saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@PostMapping("/login")
	public String login(@RequestBody CustomerDTO custDto){
		return custService.login(custDto.getEmail(), custDto.getPwd());
	}
	
	@PostMapping("/pwd/reset")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPwdDTO resetPwdDto) {
		String response = custService.resetPwd(resetPwdDto);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/pwd/forgot")
	public ResponseEntity<String> forgotPassword(@RequestBody CustomerDTO dto) {
	    String response = custService.forgotPassword(dto.getEmail());
	    return ResponseEntity.ok(response);
	}

}
