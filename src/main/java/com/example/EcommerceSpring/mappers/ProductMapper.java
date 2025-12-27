package com.example.EcommerceSpring.mappers;

import com.example.EcommerceSpring.dto.CategoryDTO;
import com.example.EcommerceSpring.dto.FakeStoreProductResponse;
import com.example.EcommerceSpring.dto.ProductWithCategoryDTO;
import com.example.EcommerceSpring.entity.Category;
import com.example.EcommerceSpring.entity.Products;
import com.example.EcommerceSpring.services.CategoryService;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // ========== DTO → Entity (For saving API data to DB) ==========

    public Products toEntity(FakeStoreProductResponse dto) {
        if (dto == null) {
            return null;
        }

        Category category = null;
        if (dto.getCategory() != null && !dto.getCategory().trim().isEmpty()) {
            category = categoryService.getOrCreateCategory(dto.getCategory());
        } else {
            throw new IllegalArgumentException("Product must have a category");
        }

        return Products.builder()
                .image(dto.getImage())
                .title(dto.getTitle())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .category(category)
                .build();
    }

    public List<Products> toEntityList(List<FakeStoreProductResponse> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    // ========== Entity → DTO (Flat structure) ==========

    public FakeStoreProductResponse toDTO(Products entity) {
        if (entity == null) {
            return null;
        }

        FakeStoreProductResponse dto = new FakeStoreProductResponse();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());

        if (entity.getCategory() != null) {
            dto.setCategoryId(entity.getCategory().getId());
            dto.setCategory(entity.getCategory().getName());
        }

        return dto;
    }

    public List<FakeStoreProductResponse> toDTOList(List<Products> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ========== Entity → ProductDetailsDTO (Nested category object) ==========

    /**
     * Convert Entity to ProductDetailsDTO with nested category object
     * Used for /api/products/{id}/details endpoint
     */
    public ProductWithCategoryDTO toDetailsDTO(Products entity) {
        if (entity == null) {
            return null;
        }

        ProductWithCategoryDTO dto = new ProductWithCategoryDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());

        // Create nested category object
        if (entity.getCategory() != null) {
            CategoryDTO categoryDTO = CategoryDTO.builder()
                    .id(entity.getCategory().getId())
                    .name(entity.getCategory().getName())
                    .build();
            dto.setCategory(categoryDTO);
        }

        return dto;
    }

    public List<ProductWithCategoryDTO> toDetailsDTOList(List<Products> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDetailsDTO)
                .collect(Collectors.toList());
    }

    // ========== Update Operations ==========

    public void updateEntityFromDTO(Products entity, FakeStoreProductResponse dto) {
        if (entity == null || dto == null) {
            return;
        }

        if (dto.getTitle() != null) entity.setTitle(dto.getTitle());
        if (dto.getPrice() != null) entity.setPrice(dto.getPrice());
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
        if (dto.getImage() != null) entity.setImage(dto.getImage());

        if (dto.getCategory() != null && !dto.getCategory().trim().isEmpty()) {
            Category category = categoryService.getOrCreateCategory(dto.getCategory());
            entity.setCategory(category);
        }
    }
}