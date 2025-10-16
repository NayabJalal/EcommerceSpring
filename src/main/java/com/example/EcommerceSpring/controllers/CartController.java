package com.example.EcommerceSpring.controllers;

import com.example.EcommerceSpring.Gateway.FakeStoreCartGateway;
import com.example.EcommerceSpring.dto.FakeStoreCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private FakeStoreCartGateway cartGateway;

    @GetMapping
    public List<FakeStoreCartResponse> getAllCarts() throws IOException {
        return cartGateway.getAllCarts();
    }

    @GetMapping("/{id}")
    public FakeStoreCartResponse getCartById(@PathVariable Long id) throws IOException {
        return cartGateway.getCartById(id);
    }
}
