package in.mk.auth.service;

import in.mk.auth.dtos.LoginRequest;
import in.mk.auth.dtos.RegisterRequest;

public interface AuthService {
	
	String login(LoginRequest request);
	void register(RegisterRequest request);

}
