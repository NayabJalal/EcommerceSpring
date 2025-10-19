package com.example.EcommerceSpring.Gateway;

import com.example.EcommerceSpring.Gateway.api.FakeStoreCartApi;
import com.example.EcommerceSpring.dto.FakeStoreCartResponse;
import com.example.EcommerceSpring.mappers.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

@Component
public class FakeStoreCartGateway implements ICartGateway {

    private final FakeStoreCartApi cartApi;
    private final CartMapper cartMapper; // ← ADD MAPPER

    @Autowired
    public FakeStoreCartGateway(Retrofit retrofit, CartMapper cartMapper) {
        this.cartApi = retrofit.create(FakeStoreCartApi.class);
        this.cartMapper = cartMapper; // ← INJECT MAPPER
    }

    public List<FakeStoreCartResponse> getAllCarts() throws IOException {
        // 1. Call API
        List<FakeStoreCartResponse> response = cartApi.getAllCarts().execute().body();

        // 2. Validate
        if (response == null) {
            throw new IOException("Failed to fetch carts from FakeStore API");
        }

        // 3. Use mapper for transformations
        return cartMapper.toDTOList(response);
    }

    public FakeStoreCartResponse getCartById(Long id) throws IOException {
        // 1.Call API
        FakeStoreCartResponse response = cartApi.getCartById(id).execute().body();

        // 2.Validate
        if (response == null) {
            throw new IOException("Cart with id " + id + " not found");
        }

        // 3. Use mapper for single cart
        return cartMapper.toDTO(response);
    }

    @Override
    public List<FakeStoreCartResponse> fetchAllCarts() throws IOException {
        // this should call getAllCarts(),not return empty list...
        return getAllCarts();
    }
}