package in.mk.auth.dtos;

import lombok.Data;

@Data
public class UserCreateRequest {
	
	private String username;
	private String password;
	private String email;
	private String role;

}
