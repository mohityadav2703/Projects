package in.mohit.maxbi.dto;

import lombok.Data;

@Data
public class SupportTicketDTO {

	private Long id;
	private UserDTO user;
	private String issue;
	private String status;

}
