package com.example.EcommerceSpring.Gateway;

import com.example.EcommerceSpring.Gateway.api.FakeStoreProductApi;
import com.example.EcommerceSpring.dto.FakeStoreProductResponse;
import com.example.EcommerceSpring.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

@Component
public class FakeStoreProductGateway implements IProductGateway {

    private final FakeStoreProductApi productApi;
    private final ProductMapper productMapper; // ← Inject mapper

    @Autowired
    public FakeStoreProductGateway(Retrofit retrofit, ProductMapper productMapper) {
        this.productApi = retrofit.create(FakeStoreProductApi.class);
        this.productMapper = productMapper;
    }

    public List<FakeStoreProductResponse> getAllProducts() throws IOException {
        List<FakeStoreProductResponse> response = productApi.getAllProducts().execute().body();
        // Use mapper to apply business logic transformations
        return productMapper.toDTOList(response);
    }

    public FakeStoreProductResponse getProductById(Long id) throws IOException {
        FakeStoreProductResponse response = productApi.getProductById(id).execute().body();
        // Use mapper for single product
        return productMapper.toDTO(response);
    }

    @Override
    public List<FakeStoreProductResponse> fetchAllProducts() throws IOException {
        return getAllProducts();
    }
}