package com.example.EcommerceSpring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;
}