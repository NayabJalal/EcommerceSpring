package com.example.EcommerceSpring.Gateway.api;

import com.example.EcommerceSpring.dto.FakeStoreCartResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.util.List;

public interface FakeStoreCartApi {

    @GET("carts")
    Call<List<FakeStoreCartResponse>> getAllCarts();

    @GET("carts/{id}")
    Call<FakeStoreCartResponse> getCartById(@Path("id") Long id);
}
