package in.mohit.maxbi.dto;

import lombok.Data;

@Data
public class AddressDTO {

	private Long id;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipCode;

}
