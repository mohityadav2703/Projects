package in.mohit.app.customer.response;

import in.mohit.app.customer.dto.CustomerDTO;
import lombok.Data;

@Data
public class AuthResponse{
	
	private CustomerDTO customer;	
	private String token;
}
