package com.example.EcommerceSpring.Gateway.api;

import com.example.EcommerceSpring.dto.ProductDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface FakeStoreProductApi {

    @GET("products")
    Call<List<ProductDTO>> getAllProducts();

    @GET("products/{id}")
    Call<ProductDTO> getProductById(@Path("id") Long id);
}
