package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.dto.FakeStoreProductResponse;
import com.example.EcommerceSpring.entity.Products;
import com.example.EcommerceSpring.mappers.ProductMapper;
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
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ============ READ Operations ============

    @Override
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Products> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Products> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<Products> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public FakeStoreProductResponse getProductWithCategory(Long id) throws Exception {
        // Fetch product from repository
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found with id: " + id));

        // Use the NEW mapper method to convert Product to FakeStoreProductResponse
        return ProductMapper.toFakeStoreProductResponse(product);
    }

    // ============ CREATE Operations ============

    @Override
    @Transactional
    public Products saveProduct(Products product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public List<Products> saveAllProducts(List<Products> products) {
        return productRepository.saveAll(products);
    }

    // ============ UPDATE Operations ============

    @Override
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

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // ============ Utility Methods ============

    @Override
    public boolean productExists(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public long getProductCount() {
        return productRepository.count();
    }
}