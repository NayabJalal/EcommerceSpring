package com.example.EcommerceSpring.repository;

import com.example.EcommerceSpring.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    // JpaRepository provides all CRUD operations
    // save(), findAll(), findById(), delete(), etc.
    Optional<Products> findByTitle(String title);
    List<Products> findByCategory(String category);
    List<Products> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Products> findByPriceGreaterThan(Double price);
    List<Products> findByPriceLessThan(Double price);
}