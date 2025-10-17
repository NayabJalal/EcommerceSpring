package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.Gateway.ICategoryGateway;
import com.example.EcommerceSpring.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service // @Service is a specialized form of @Component, used to mark classes that contain business logic or service layer code.
public class FakestoreCategoryService implements ICategoryService{


    //Loose Coupling
    private final ICategoryGateway categoryGateway;
    // Spring injects FakeStoreCategoryGateway here
    public FakestoreCategoryService(ICategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public List<CategoryDTO> getCategoriesService() throws IOException {
        // ↓ CALLS: ICategoryGateway.getAllCategories()
        return this.categoryGateway.getAllCategories();
    }

}
