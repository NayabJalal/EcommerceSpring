package com.example.EcommerceSpring.repository;
import com.example.EcommerceSpring.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    List<Products> findByCategoryName(String categoryName);

    List<Products> findByPriceBetween(Double minPrice, Double maxPrice);
}