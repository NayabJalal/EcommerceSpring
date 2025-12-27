package com.example.EcommerceSpring.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductWithCategoryDTO {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private Long categoryId;
    private String category;
    private String image;
}
