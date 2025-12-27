package com.example.EcommerceSpring.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductWithCategoryDTO {
    private Long id;
    private String image;
    private String title;
    private Double price;
    private String description;
    private CategoryDTO category;
}
