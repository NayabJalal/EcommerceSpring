package com.example.EcommerceSpring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Column(length = 500)
    private String image;

    // Each product belongs to one category
    // or we can say -> One category can hava many products
    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false) //foreign key named as categoryId in here
    private Category category;
}