package com.example.EcommerceSpring.mappers;

import com.example.EcommerceSpring.dto.CategoryDTO;
import com.example.EcommerceSpring.entity.Category;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            return null;
        }
        return CategoryDTO.builder()
                .name(categoryName)
                .build();
    }

    public CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public List<CategoryDTO> toDTOList(List<String> categoryNames) {
        if (categoryNames == null || categoryNames.isEmpty()) {
            return List.of();
        }
        return categoryNames.stream()
                .map(this::toDTO)
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    public List<CategoryDTO> toDTOListFromEntities(List<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            return List.of();
        }
        return categories.stream()
                .map(this::toDTO)
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    public Category toEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        return Category.builder()
                .name(categoryDTO.getName())
                .build();
    }
}