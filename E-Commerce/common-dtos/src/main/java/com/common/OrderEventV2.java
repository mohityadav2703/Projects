package com.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEventV2 {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private String userEmail;
    private OrderEventType type;
}
