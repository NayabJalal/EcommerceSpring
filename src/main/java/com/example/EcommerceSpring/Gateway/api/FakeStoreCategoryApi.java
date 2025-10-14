package com.example.EcommerceSpring.Gateway.api;

import retrofit2.Call;
import retrofit2.http.GET;

import java.io.IOException;

public interface FakeStoreCategoryApi {

    @GET("/products/category")
    Call <FakeStoreCategoryApi> getallfakeStoreCategories() throws IOException;

}
