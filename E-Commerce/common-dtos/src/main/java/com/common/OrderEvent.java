package com.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

    private Long orderId;
    private Long productId;
    private Integer quantity;
    private String status;
    // ORDER_CREATED
    // ORDER_CANCELLED
    // INVENTORY_RESERVED
    // INVENTORY_FAILED
    // INVENTORY_RELEASED
}
