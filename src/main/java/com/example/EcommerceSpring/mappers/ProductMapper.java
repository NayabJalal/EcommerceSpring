package com.example.EcommerceSpring.mappers;

import com.example.EcommerceSpring.dto.FakeStoreProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    /**
     * Convert FakeStoreProductResponse to internal ProductDTO
     * (You can create a ProductDTO if needed, or keep using FakeStoreProductResponse)
     */
    public FakeStoreProductResponse toDTO(FakeStoreProductResponse apiResponse) {
        if (apiResponse == null) {
            return null;
        }

        // For now, just passing through
        // In future, you might convert to internal ProductDTO
        return apiResponse;
    }

    /**
     * Convert List of products
     */
    public List<FakeStoreProductResponse> toDTOList(List<FakeStoreProductResponse> apiResponses) {
        if (apiResponses == null || apiResponses.isEmpty()) {
            return List.of();
        }

        return apiResponses.stream()
                .map(this::toDTO)
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    /**
     * Apply business transformations
     * Example: Add discounts, format prices, etc.
     */
    public FakeStoreProductResponse applyBusinessLogic(FakeStoreProductResponse product) {
        if (product == null) {
            return null;
        }

        // Example: Apply 10% discount for products over $100
        if (product.getPrice() != null && product.getPrice() > 100) {
            product.setPrice(product.getPrice() * 0.9);
        }

        return product;
    }
}