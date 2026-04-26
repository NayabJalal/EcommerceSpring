package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.dto.CategoryDTO;
import com.example.EcommerceSpring.entity.Category;

import java.io.IOException;
import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> getCategoriesService() throws IOException;//Any service implementing this MUST provide getCategoriesService()
    Category updateCategory(Long id, Category updatedCategory);
}
