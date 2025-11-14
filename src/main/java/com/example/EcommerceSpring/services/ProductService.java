package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.entity.Products;
import com.example.EcommerceSpring.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service layer that ONLY interacts with Repository (Database)
 * No API calls here - that's handled by ProductSyncService
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ============ READ Operations ============

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Products> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Products> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    public List<Products> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // ============ CREATE Operations ============

    @Transactional
    public Products saveProduct(Products product) {
        return productRepository.save(product);
    }

    @Transactional
    public List<Products> saveAllProducts(List<Products> products) {
        return productRepository.saveAll(products);
    }

    // ============ UPDATE Operations ============

    @Transactional
    public Products updateProduct(Long id, Products updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setTitle(updatedProduct.getTitle());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    existingProduct.setCategory(updatedProduct.getCategory());
                    existingProduct.setImage(updatedProduct.getImage());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // ============ DELETE Operations ============

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // ============ Utility Methods ============

    public boolean productExists(Long id) {
        return productRepository.existsById(id);
    }

    public long getProductCount() {
        return productRepository.count();
    }
}