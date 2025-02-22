package in.mohit.app.customer.dto;

import lombok.Data;

@Data
public class ResetPwdDTO {

	private String email;
	private String oldPwd;
	private String newPwd;
	private String confirmPwd;
}
