package com.example.EcommerceSpring.Gateway;

import com.example.EcommerceSpring.dto.FakeStoreProductResponse;
import java.io.IOException;
import java.util.List;

public interface IProductGateway {
    List<FakeStoreProductResponse> fetchAllProducts() throws IOException;
}
