package com.example.EcommerceSpring.Gateway;

import com.example.EcommerceSpring.Gateway.api.FakeStoreCartApi;
import com.example.EcommerceSpring.dto.FakeStoreCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import java.io.IOException;
import java.util.List;

@Component
public class FakeStoreCartGateway {

    private final FakeStoreCartApi cartApi;

    @Autowired
    public FakeStoreCartGateway(Retrofit retrofit) {
        this.cartApi = retrofit.create(FakeStoreCartApi.class);
    }

    public List<FakeStoreCartResponse> getAllCarts() throws IOException {
        return cartApi.getAllCarts().execute().body();
    }

    public FakeStoreCartResponse getCartById(Long id) throws IOException {
        return cartApi.getCartById(id).execute().body();
    }
}

