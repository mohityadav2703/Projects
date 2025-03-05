package in.mohit.app.customer.mapper;

import org.modelmapper.ModelMapper;

import in.mohit.app.customer.dto.CustomerDTO;
import in.mohit.app.customer.entity.Customer;

public class CustomerMapper {

	private static final ModelMapper modelMapper=new ModelMapper();
	
	
	public static CustomerDTO convertToDTO(Customer customerEntity) {
		return modelMapper.map(customerEntity, CustomerDTO.class);
	}

	
	public static Customer convertToEntity(CustomerDTO customerDto) {
		return modelMapper.map(customerDto, Customer.class);
	}

}
