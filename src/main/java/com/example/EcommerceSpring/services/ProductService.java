package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.dto.ProductWithCategoryDTO;
import com.example.EcommerceSpring.entity.Products;
import com.example.EcommerceSpring.exception.ProductNotFoundException;
import com.example.EcommerceSpring.mappers.ProductMapper;
import com.example.EcommerceSpring.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    // ============ READ Operations ============

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Products> getProductById(Long id) {
        Products products = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID "+ id +" not found :) "));
        return productRepository.findById(id);
    }

    public List<Products> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    public List<Products> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Get product with nested category object
     * Returns ProductDetailsDTO with category as object
     */
    public ProductWithCategoryDTO getProductWithCategoryDetails(Long id) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        return productMapper.toDetailsDTO(product);
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