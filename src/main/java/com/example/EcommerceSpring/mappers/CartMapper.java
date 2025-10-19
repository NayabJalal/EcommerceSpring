package com.example.EcommerceSpring.mappers;

import com.example.EcommerceSpring.dto.FakeStoreCartResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    /**
     * Convert cart response from API to internal DTO
     */
    public FakeStoreCartResponse toDTO(FakeStoreCartResponse apiResponse) {
        if (apiResponse == null) {
            return null;
        }

        // Add any transformations here
        return apiResponse;
    }

    /**
     * Convert list of carts
     */
    public List<FakeStoreCartResponse> toDTOList(List<FakeStoreCartResponse> apiResponses) {
        if (apiResponses == null || apiResponses.isEmpty()) {
            return List.of();
        }

        return apiResponses.stream()
                .map(this::toDTO)
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    /**
     * Calculate total cart value
     */
    public double calculateCartTotal(FakeStoreCartResponse cart) {
        if (cart == null || cart.getProducts() == null) {
            return 0.0;
        }

        // This is simplified - in real app you'd fetch product prices
        return cart.getProducts().stream()
                .mapToInt(FakeStoreCartResponse.CartItem::getQuantity)
                .sum();
    }
}