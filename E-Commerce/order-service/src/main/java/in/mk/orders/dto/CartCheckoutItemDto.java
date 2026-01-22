package in.mk.orders.dto;

import lombok.Data;

@Data
public class CartCheckoutItemDto {
    private Long productId;
    private Integer quantity;
}
