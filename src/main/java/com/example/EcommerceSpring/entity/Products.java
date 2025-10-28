package com.example.EcommerceSpring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Products extends BaseEntity {
    @Column(nullable = false, length = 500)
    private String title;
    @Column(nullable = false)
    private Double price;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(length = 100)
    private String category;
    @Column(length = 500)
    private String image;
}