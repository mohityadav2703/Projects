package in.mk.orders.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.mk.orders.dto.CartCheckoutItemDto;
import in.mk.orders.entity.Order;
import in.mk.orders.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    // ---------------- USER APIs ----------------

    @PostMapping("/place")
    @PreAuthorize("hasRole('USER')")
    public Order placeOrder(
            @RequestParam("productId") Long productId,
            @RequestParam("qty") Integer qty) 
    {
        return service.placeOrder(productId, qty);
    }

    @PostMapping("/checkout")
    @PreAuthorize("hasRole('USER')")
    public List<Order> checkout() {
        return service.checkout();
    }
    
    @PostMapping("/checkout/partial")
    @PreAuthorize("hasRole('USER')")
    public List<Order> checkoutPartial(
            @RequestBody List<CartCheckoutItemDto> items) {
        return service.checkoutPartial(items);
    }

    // ---------------- ADMIN / SYSTEM APIs ----------------

    @PostMapping("/confirm/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void confirm(@PathVariable("id") Long id) {
        service.confirm(id);
    }

    @PostMapping("/deliver/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deliver(@PathVariable("id") Long id) {
        service.deliver(id);
    }

    @PostMapping("/cancel/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void cancel(@PathVariable("id") Long id) {
        service.cancel(id);
    }

    @PostMapping("/return/{id}")
    @PreAuthorize("hasRole('USER')")
    public void requestReturn(@PathVariable("id") Long id) {
        service.requestReturn(id);
    }

    @PostMapping("/returned/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void markReturned(@PathVariable("id") Long id) {
        service.markReturned(id);
    }
}
