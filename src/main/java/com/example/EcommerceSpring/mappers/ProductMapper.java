package com.example.EcommerceSpring.mappers;

import com.example.EcommerceSpring.dto.FakeStoreProductResponse;
import com.example.EcommerceSpring.entity.Products;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    // ============ DTO → Entity (For saving API data to DB) ============

    /**
     * Convert FakeStoreProductResponse (DTO) → Products (Entity)
     */
    public Products toEntity(FakeStoreProductResponse dto) {
        if (dto == null) {
            return null;
        }

        return Products.builder()
                .title(dto.getTitle())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .image(dto.getImage())
                .build();
    }

    /**
     * Convert List<DTO> → List<Entity>
     */
    public List<Products> toEntityList(List<FakeStoreProductResponse> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }

        return dtos.stream()
                .map(this::toEntity)
                .filter(entity -> entity != null)
                .collect(Collectors.toList());
    }

    // ============ Entity → DTO (For returning DB data to client) ============

    /**
     * Convert Products (Entity) → FakeStoreProductResponse (DTO)
     */
    public FakeStoreProductResponse toDTO(Products entity) {
        if (entity == null) {
            return null;
        }

        FakeStoreProductResponse dto = new FakeStoreProductResponse();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setCategory(entity.getCategory());
        dto.setImage(entity.getImage());

        return dto;
    }

    /**
     * Convert List<Entity> → List<DTO>
     */
    public List<FakeStoreProductResponse> toDTOList(List<Products> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(this::toDTO)
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    // ============ Update Operations ============

    /**
     * Update existing entity with DTO data (for PUT/PATCH operations)
     */
    public void updateEntityFromDTO(Products entity, FakeStoreProductResponse dto) {
        if (entity == null || dto == null) {
            return;
        }

        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setCategory(dto.getCategory());
        entity.setImage(dto.getImage());
    }
}