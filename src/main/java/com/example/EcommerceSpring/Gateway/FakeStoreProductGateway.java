package com.example.EcommerceSpring.Gateway;

import com.example.EcommerceSpring.Gateway.api.FakeStoreProductApi;
import com.example.EcommerceSpring.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

/**
 * Gateway for FakeStore Product API
 * Responsibility: Make API calls and return DTOs (no entity conversion here)
 */
@Component
public class FakeStoreProductGateway implements IProductGateway {

    private final FakeStoreProductApi productApi;

    @Autowired
    public FakeStoreProductGateway(Retrofit retrofit) {
        this.productApi = retrofit.create(FakeStoreProductApi.class);
    }

    /**
     * Fetch all products from FakeStore API
     * Returns: List of DTOs (no conversion)
     */
    public List<ProductDTO> getAllProducts() throws IOException {
        List<ProductDTO> response = productApi.getAllProducts().execute().body();

        if (response == null) {
            throw new IOException("Failed to fetch products from FakeStore API");
        }

        return response;
    }

    /**
     * Fetch single product by ID from FakeStore API
     * Returns: Single DTO (no conversion)
     */
    public ProductDTO getProductById(Long id) throws IOException {
        ProductDTO response = productApi.getProductById(id).execute().body();

        if (response == null) {
            throw new IOException("Product with id " + id + " not found");
        }
        return response;
    }

    @Override
    public List<ProductDTO> fetchAllProducts() throws IOException {
        return getAllProducts();
    }
}