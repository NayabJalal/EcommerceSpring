package com.example.EcommerceSpring.controllers;

import com.example.EcommerceSpring.Gateway.FakeStoreProductGateway;
import com.example.EcommerceSpring.dto.FakeStoreProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private FakeStoreProductGateway productGateway;

    @GetMapping
    public List<FakeStoreProductResponse> getAllProducts() throws IOException {
        return productGateway.getAllProducts();
    }

    @GetMapping("/{id}")
    public FakeStoreProductResponse getProductById(@PathVariable Long id) throws IOException {
        return productGateway.getProductById(id);
    }
}
