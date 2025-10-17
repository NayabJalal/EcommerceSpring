package com.example.EcommerceSpring.Gateway;

import com.example.EcommerceSpring.Gateway.api.FakeStoreCategoryApi;
import com.example.EcommerceSpring.dto.CategoryDTO;
import com.example.EcommerceSpring.dto.FakeStoreCategoryResponseDTO;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class FakeStoreCategoryGateway implements ICategoryGateway {

    private final FakeStoreCategoryApi fakeStoreCategoryApi;

    public FakeStoreCategoryGateway(FakeStoreCategoryApi fakeStoreCategoryApi) {
        this.fakeStoreCategoryApi = fakeStoreCategoryApi;
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {
        List<String> response =  this.fakeStoreCategoryApi
                .getAllFakeStoreCategories()
                .execute()
                .body();
        if(response == null || response.isEmpty()){
            throw new IOException("Failed to fetch Categories from FakeStore Api");
        }
        // Transform external API response to internal DTO(List<String> to CategoryDTO)
        return response.stream()
                 .map(categoryName -> CategoryDTO.builder()
                            .name(categoryName)
                            .build())
        .toList();
    }
}
