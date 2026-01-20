package in.mk.cart.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import in.mk.cart.dto.CartItemRequest;
import in.mk.cart.dto.CartItemResponse;
import in.mk.cart.service.CartService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    // ---------------- ADD TO CART ----------------

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public void add(@RequestBody CartItemRequest request) {
        service.add(request);
    }

    // ---------------- VIEW CART ----------------

    @GetMapping("/view")
    @PreAuthorize("hasRole('USER')")
    public List<CartItemResponse> view() {
        return service.view();
    }

    // ---------------- CLEAR CART ----------------

    @DeleteMapping("/clear")
    @PreAuthorize("hasRole('USER')")
    public void clear() {
        service.clear();
    }
}
