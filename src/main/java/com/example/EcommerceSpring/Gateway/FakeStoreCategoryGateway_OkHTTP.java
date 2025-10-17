package com.example.EcommerceSpring.Gateway;

import com.example.EcommerceSpring.dto.CategoryDTO;
import com.example.EcommerceSpring.dto.FakeStoreCategoryResponseDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@Component("Nayab")
//@Primary //Used on a component-scanned class (like a service or gateway) to make it the default bean among multiple implementations.
public class FakeStoreCategoryGateway_OkHTTP implements ICategoryGateway{

    private final RestTemplateBuilder restTemplateBuilder;

    public FakeStoreCategoryGateway_OkHTTP(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder; //constructor injection of rest template builder
    }
//1. Call a restTemplate function()
    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "https://fakestoreapi.com/products/categories";

        // FakeStore returns String[], not a complex object
       ResponseEntity<String[]> response = restTemplate.getForEntity(url, String[].class);
       if (response.getBody() == null){
           throw new IOException("Failed to fetch categories from fakestore API");
       }
        return Arrays.stream(response.getBody())
               .map(category -> CategoryDTO.builder()
                       .name(category)
                       .build())
               .toList();
    }
}
