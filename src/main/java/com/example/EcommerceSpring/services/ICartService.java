package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.dto.FakeStoreCartResponse;
import java.io.IOException;
import java.util.List;

public interface ICartService {
    List<FakeStoreCartResponse> getAllCarts() throws IOException;
}
