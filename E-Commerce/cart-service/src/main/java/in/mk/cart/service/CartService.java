package in.mk.cart.service;

import java.util.List;

import in.mk.cart.dto.CartItemRequest;
import in.mk.cart.dto.CartItemResponse;

public interface CartService {

    void add(CartItemRequest request);

    List<CartItemResponse> view();

    // 🔥 NEW – event-friendly method
    void removeItem(
        String userEmail,
        Long productId,
        Integer quantity
    );

    void clear();

    void clearByUser(String userEmail);
}
