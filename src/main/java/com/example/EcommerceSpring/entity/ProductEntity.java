package com.example.EcommerceSpring.entity;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductEntity extends BaseEntity{
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
}
