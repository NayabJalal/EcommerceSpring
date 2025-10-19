package com.example.EcommerceSpring.mappers;

import com.example.EcommerceSpring.dto.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    /**
     * Convert a single category name (String) to CategoryDTO
     */
    public CategoryDTO toDTO(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            return null;
        }

        return CategoryDTO.builder()
                .name(categoryName)
                .build();
    }

    /**
     * Convert List of category names to List of CategoryDTO
     */
    public List<CategoryDTO> toDTOList(List<String> categoryNames) {
        if (categoryNames == null || categoryNames.isEmpty()) {
            return List.of();
        }

        return categoryNames.stream()
                .map(this::toDTO)
                .filter(dto -> dto != null) // Remove null values
                .collect(Collectors.toList());
    }

    /**
     * Convert CategoryDTO back to String (for reverse mapping)
     */
    public String fromDTO(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        return categoryDTO.getName();
    }

    /**
     * Convert List of CategoryDTO back to List of Strings
     */
    public List<String> fromDTOList(List<CategoryDTO> categoryDTOs) {
        if (categoryDTOs == null || categoryDTOs.isEmpty()) {
            return List.of();
        }

        return categoryDTOs.stream()
                .map(this::fromDTO)
                .filter(name -> name != null)
                .collect(Collectors.toList());
    }
}