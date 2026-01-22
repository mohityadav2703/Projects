package in.mk.cart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final CartService cartService;

    // ---------------- ADD ITEM ----------------

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> add(
            @RequestBody CartItemRequest request) {

        cartService.add(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // ---------------- VIEW CART ----------------

    @GetMapping("/view")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<CartItemResponse>> view() {

        return ResponseEntity.ok(cartService.view());
    }

    // ---------------- CLEAR CART ----------------

    @DeleteMapping("/clear")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> clear() {

        cartService.clear();
        return ResponseEntity.noContent().build();
    }
}
