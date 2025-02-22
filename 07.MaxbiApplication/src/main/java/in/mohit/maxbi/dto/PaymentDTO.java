package in.mohit.maxbi.dto;

import lombok.Data;

@Data
public class PaymentDTO {

	private Long id;
    private OrderDTO order;
    private String paymentStatus;
    private String transactionId;
}
