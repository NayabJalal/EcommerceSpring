package com.example.EcommerceSpring.Gateway.api;

import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;

import java.io.IOException;
@Component
public interface FakeStoreCategoryApi {

    @GET("products/category")
    Call <FakeStoreCategoryApi> getallfakeStoreCategories() throws IOException;

}
