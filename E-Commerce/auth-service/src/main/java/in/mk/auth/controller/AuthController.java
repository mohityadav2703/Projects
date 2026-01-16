package in.mk.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.mk.auth.dtos.AuthResponse;
import in.mk.auth.dtos.LoginRequest;
import in.mk.auth.dtos.RegisterRequest;
import in.mk.auth.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest request) {
		authService.register(request);
		return "User registered successfully";
	}
	
	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest request) {
		return new AuthResponse(authService.login(request));
	}
}
