package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.Gateway.IProductGateway;
import com.example.EcommerceSpring.dto.FakeStoreProductResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    private final IProductGateway productGateway;

    public FakeStoreProductService(IProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public List<FakeStoreProductResponse> getAllProducts() throws IOException {
        return this.productGateway.fetchAllProducts();
    }
}
