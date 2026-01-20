package in.mk.cart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import in.mk.cart.dto.CartItemRequest;
import in.mk.cart.dto.CartItemResponse;
import in.mk.cart.repository.CartRepository;
import in.mk.cart.service.CartService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository repository;

    private String currentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public void add(CartItemRequest request) {
        repository.addItem(
            currentUser(),
            request.getProductId(),
            request.getQuantity()
        );
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public List<CartItemResponse> view() {

        Map<Object, Object> map = repository.getCart(currentUser());

        return map.entrySet().stream()
                .map(e -> new CartItemResponse(
                        Long.valueOf(e.getKey().toString()),
                        Integer.parseInt(e.getValue().toString())
                ))
                .toList();
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public void clear() {
        repository.clear(currentUser());
    }
    
    @Override
    public void clearByUser(String userEmail) {
    	repository.clear(userEmail);
    }
}
