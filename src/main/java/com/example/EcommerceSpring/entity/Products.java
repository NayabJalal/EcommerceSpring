package com.example.EcommerceSpring.entity;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Products extends BaseEntity {
    //private Long id; -> it will be created am(BaseEntity).
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
}
