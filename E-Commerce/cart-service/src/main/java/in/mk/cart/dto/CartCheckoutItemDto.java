package in.mk.cart.dto;

import lombok.Data;

@Data
public class CartCheckoutItemDto {
    private Long productId;
    private Integer quantity;
}
