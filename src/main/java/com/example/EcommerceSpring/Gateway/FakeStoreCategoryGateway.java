package com.example.EcommerceSpring.Gateway;

import com.example.EcommerceSpring.Gateway.api.FakeStoreCategoryApi;
import com.example.EcommerceSpring.dto.CategoryDTO;
import com.example.EcommerceSpring.dto.FakeStoreCategoryRersponseDTO;
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
        FakeStoreCategoryRersponseDTO response = (FakeStoreCategoryRersponseDTO) this.fakeStoreCategoryApi.getallfakeStoreCategories().execute().body();
        if(response == null){
            throw new IOException("Failed to fetch Categories from FakeStore Api");
        }
        return response.getCategories().stream()
                 .map(category -> CategoryDTO.builder()
                            .name(category)
                            .build())
        .toList();
    }
}
