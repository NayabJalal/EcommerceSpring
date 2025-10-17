package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.Gateway.ICartGateway;
import com.example.EcommerceSpring.dto.FakeStoreCartResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FakeStoreCartService implements ICartService {

    private final ICartGateway cartGateway;

    public FakeStoreCartService(ICartGateway cartGateway) {
        this.cartGateway = cartGateway;
    }

    @Override
    public List<FakeStoreCartResponse> getAllCarts() throws IOException {
        return this.cartGateway.fetchAllCarts();
    }
}
