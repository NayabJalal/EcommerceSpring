package com.example.EcommerceSpring.Gateway;

import com.example.EcommerceSpring.Gateway.api.FakeStoreProductApi;
import com.example.EcommerceSpring.dto.FakeStoreProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

@Component
public class FakeStoreProductGateway implements IProductGateway{
    private final FakeStoreProductApi productApi;

    @Autowired
    public FakeStoreProductGateway(Retrofit retrofit) {
        this.productApi = retrofit.create(FakeStoreProductApi.class);
    }

    public List<FakeStoreProductResponse> getAllProducts() throws IOException {
        return productApi.getAllProducts().execute().body();
    }

    public FakeStoreProductResponse getProductById(Long id) throws IOException {
        return productApi.getProductById(id).execute().body();
    }

    @Override
    public List<FakeStoreProductResponse> fetchAllProducts() throws IOException {
        return List.of();
    }
}

