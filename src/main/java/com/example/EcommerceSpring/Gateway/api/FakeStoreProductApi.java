package com.example.EcommerceSpring.Gateway.api;

import com.example.EcommerceSpring.dto.FakeStoreProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface FakeStoreProductApi {

    @GET("products")
    Call<List<FakeStoreProductResponse>> getAllProducts();

    @GET("products/{id}")
    Call<FakeStoreProductResponse> getProductById(@Path("id") Long id);
}
