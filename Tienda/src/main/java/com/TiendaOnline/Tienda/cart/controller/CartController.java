package com.tiendaonline.tienda.cart.controller;

import com.tiendaonline.tienda.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<Void> add(@PathVariable Long productId) {
        service.addProduct(productId);
        return ResponseEntity.ok().build();
    }
}
