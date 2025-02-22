package in.mohit.maxbi.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
	
	private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private List<AddressDTO> addresses;


}
