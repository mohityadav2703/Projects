package in.mk.orders.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private Long productId;
    private Integer quantity;
}
