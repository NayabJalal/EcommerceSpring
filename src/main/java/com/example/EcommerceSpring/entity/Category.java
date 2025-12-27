package com.example.EcommerceSpring.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(
            mappedBy = "category",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    @Builder.Default
    private List<Products> products = new ArrayList<>();
}