package com.example.EcommerceSpring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponse {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private Long categoryId;
    private String category;
    private String image;
}