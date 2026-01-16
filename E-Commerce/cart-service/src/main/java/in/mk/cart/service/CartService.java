package in.mk.cart.service;

import java.util.List;

import in.mk.cart.dto.CartItemRequest;
import in.mk.cart.dto.CartItemResponse;

public interface CartService {

    void add(CartItemRequest request);

    List<CartItemResponse> view();

    void remove(Long productId);

    void clear();
}
