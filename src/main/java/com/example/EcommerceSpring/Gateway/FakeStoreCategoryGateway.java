package com.example.EcommerceSpring.Gateway;

import com.example.EcommerceSpring.Gateway.api.FakeStoreCategoryApi;
import com.example.EcommerceSpring.dto.CategoryDTO;
import com.example.EcommerceSpring.mappers.CategoryMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class FakeStoreCategoryGateway implements ICategoryGateway {

    private final FakeStoreCategoryApi fakeStoreCategoryApi;
    private final CategoryMapper categoryMapper; // ← Inject mapper

    // Constructor injection
    public FakeStoreCategoryGateway(FakeStoreCategoryApi fakeStoreCategoryApi, CategoryMapper categoryMapper) {
        this.fakeStoreCategoryApi = fakeStoreCategoryApi;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {
        // 1. Call API
        List<String> response = this.fakeStoreCategoryApi
                .getAllFakeStoreCategories()
                .execute()
                .body();

        // 2. Validate
        if (response == null || response.isEmpty()) {
            throw new IOException("Failed to fetch Categories from FakeStore API");
        }
        // 3. Use mapper to transform
        return categoryMapper.toDTOList(response);
    }
}