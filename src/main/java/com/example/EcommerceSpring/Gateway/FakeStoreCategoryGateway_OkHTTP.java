package com.example.EcommerceSpring.Gateway;

import com.example.EcommerceSpring.dto.CategoryDTO;
import com.example.EcommerceSpring.mappers.CategoryMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component("Nayab")
public class FakeStoreCategoryGateway_OkHTTP implements ICategoryGateway {

    private final RestTemplateBuilder restTemplateBuilder;
    private final CategoryMapper categoryMapper; // ← Inject mapper

    public FakeStoreCategoryGateway_OkHTTP(
            RestTemplateBuilder restTemplateBuilder,
            CategoryMapper categoryMapper) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "https://fakestoreapi.com/products/categories";
        // 1. Call API
        ResponseEntity<String[]> response = restTemplate.getForEntity(url, String[].class);
        // 2. Validate
        if (response.getBody() == null) {
            throw new IOException("Failed to fetch categories from FakeStore API");
        }
        // 3. Use mapper
        return categoryMapper.toDTOList(Arrays.asList(response.getBody()));
    }
}