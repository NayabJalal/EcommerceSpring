package com.example.EcommerceSpring.Gateway;

import com.example.EcommerceSpring.dto.FakeStoreCartResponse;
import java.io.IOException;
import java.util.List;

public interface ICartGateway {
    List<FakeStoreCartResponse> fetchAllCarts() throws IOException;
}
