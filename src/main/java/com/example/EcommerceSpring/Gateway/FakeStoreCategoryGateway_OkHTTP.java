package com.example.EcommerceSpring.Gateway;

import com.example.EcommerceSpring.dto.CategoryDTO;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
@Component("Nayab")
//@Primary //Used on a component-scanned class (like a service or gateway) to make it the default bean among multiple implementations.
public class FakeStoreCategoryGateway_OkHTTP implements ICategoryGateway{
    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {
        return List.of();
    }
}
