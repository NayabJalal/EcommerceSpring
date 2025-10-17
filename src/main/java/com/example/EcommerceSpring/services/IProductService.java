package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.dto.FakeStoreProductResponse;
import java.io.IOException;
import java.util.List;

public interface IProductService {
    List<FakeStoreProductResponse> getAllProducts() throws IOException;
}
