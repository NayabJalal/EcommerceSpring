package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.dto.ProductDTO;
import com.example.EcommerceSpring.entity.Products;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    // READ Operations
    List<Products> getAllProducts();

    Optional<Products> getProductById(Long id);

    List<Products> getProductsByCategory(String categoryName);

    List<Products> getProductsByPriceRange(Double minPrice, Double maxPrice);

    ProductDTO getProductWithCategory(Long id) throws Exception;

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
