package in.mohit.app.customer.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerDTO {
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private String updatedPassword;
	private String phNo;
	
	private Date createdDate;
	private Date updatedDate;

}
