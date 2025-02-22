package in.mohit.maxbi.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {
	
	private Long id;
    private UserDTO user;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderItemDTO> orderItems;


}
