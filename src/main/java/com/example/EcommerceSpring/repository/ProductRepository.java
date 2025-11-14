package com.example.EcommerceSpring.repository;

import com.example.EcommerceSpring.entity.Category;
import com.example.EcommerceSpring.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    // JpaRepository provides all basic CRUD:
    // save(), findAll(), findById(), deleteById(), count(), existsById()

    // Custom query methods
    Optional<Products> findByTitle(String title);

    // ✅ Fixed: Category is an object, not a String
    List<Products> findByCategory(Category category);

    List<Products> findByCategoryName(String categoryName);

    List<Products> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Products> findByPriceGreaterThan(Double price);

    List<Products> findByPriceLessThan(Double price);
}