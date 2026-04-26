package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.dto.ProductDTO;
import com.example.EcommerceSpring.dto.ProductWithCategoryDTO;
import com.example.EcommerceSpring.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    // READ Operations
    Page<Products> getAllProducts(Pageable pageable);

    Optional<Products> getProductById(Long id);

    Page<Products> getProductsByCategory(String categoryName, Pageable pageable);

    Page<Products> getProductsByPriceRange(Double minPrice, Double maxPrice, Pageable pageable);

    Page<Products> getAllProducts(String category, Pageable pageable);

    ProductWithCategoryDTO getProductWithCategory(Long id);

    // CREATE Operations
    Products saveProduct(Products product);

    List<Products> saveAllProducts(List<Products> products);

    // UPDATE Operations
    Products updateProduct(Long id, Products updatedProduct);

    // DELETE Operations
    void deleteProduct(Long id);

    // Utility Methods
    boolean productExists(Long id);

    long getProductCount();
}
