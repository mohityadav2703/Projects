package com.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRestController {

	@GetMapping("/")
	public String getMsg() {
		return "Hello Welcome to SpringBoot Web Application";
	}
}
