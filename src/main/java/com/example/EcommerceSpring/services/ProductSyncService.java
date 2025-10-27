package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.Gateway.FakeStoreProductGateway;
import com.example.EcommerceSpring.dto.FakeStoreProductResponse;
import com.example.EcommerceSpring.entity.Products;
import com.example.EcommerceSpring.mappers.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * Service responsible for syncing products from FakeStore API to Database
 * Separates API concerns from database operations
 */
@Service
public class ProductSyncService {

    private final FakeStoreProductGateway fakeStoreGateway;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductSyncService(
            FakeStoreProductGateway fakeStoreGateway,
            ProductService productService,
            ProductMapper productMapper) {
        this.fakeStoreGateway = fakeStoreGateway;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    /**
     * Sync all products from FakeStore API to local database
     */
    @Transactional
    public List<Products> syncAllProductsFromFakeStore() throws IOException {
        // 1. Fetch from FakeStore API (returns DTOs)
        List<FakeStoreProductResponse> apiProducts = fakeStoreGateway.fetchAllProducts();

        // 2. Convert DTOs to Entities using mapper
        List<Products> productEntities = productMapper.toEntityList(apiProducts);

        // 3. Save to database via ProductService
        return productService.saveAllProducts(productEntities);
    }

    /**
     * Sync single product from FakeStore API by its ID
     */
    @Transactional
    public Products syncSingleProduct(Long fakeStoreProductId) throws IOException {
        // 1. Fetch single product from API
        FakeStoreProductResponse apiProduct = fakeStoreGateway.getProductById(fakeStoreProductId);

        // 2. Convert DTO to Entity
        Products productEntity = productMapper.toEntity(apiProduct);

        // 3. Save to database
        return productService.saveProduct(productEntity);
    }
}