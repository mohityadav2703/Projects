package com.common.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEvent {

    private Long orderId;
    private Long productId;
    private Integer quantity;
    private String userEmail;
    private OrderEventType type;
    
    private OrderContext context;

}
