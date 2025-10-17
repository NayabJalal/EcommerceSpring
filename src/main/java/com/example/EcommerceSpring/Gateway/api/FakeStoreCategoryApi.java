package com.example.EcommerceSpring.Gateway.api;

import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

@Component
public interface FakeStoreCategoryApi {

    @GET("products/categories")
    Call <List<String>> getAllFakeStoreCategories();
    //Call is a class from the Retrofit library (package: retrofit2.Call).
    //It represents an HTTP request that can be executed either synchronously or asynchronously.
}
