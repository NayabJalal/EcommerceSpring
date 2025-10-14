package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.Gateway.ICategoryGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // @Service is a specialized form of @Component, used to mark classes that contain business logic or service layer code.
public class FakestoreCategoryService implements ICategoryService{


    private final ICategoryGateway categoryGateway;

    public FakestoreCategoryService(ICategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public List<String> getCategoriesService() {
        return List.of();
    }

}
