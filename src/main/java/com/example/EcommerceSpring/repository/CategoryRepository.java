package com.example.EcommerceSpring.repository;

import com.example.EcommerceSpring.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Find category by name (case-sensitive)
     */
    Optional<Category> findByName(String name);

    /**
     * Check if category exists by name
     */
    boolean existsByName(String name);
}