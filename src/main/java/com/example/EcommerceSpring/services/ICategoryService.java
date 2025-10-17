package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.dto.CategoryDTO;

import java.io.IOException;
import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> getCategoriesService() throws IOException;//Any service implementing this MUST provide getCategoriesService()
}
