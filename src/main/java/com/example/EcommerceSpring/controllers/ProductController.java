package com.example.EcommerceSpring.controllers;

import com.example.EcommerceSpring.dto.FakeStoreProductResponse;
import com.example.EcommerceSpring.dto.ProductWithCategoryDTO;
import com.example.EcommerceSpring.entity.Products;
import com.example.EcommerceSpring.mappers.ProductMapper;
import com.example.EcommerceSpring.services.ProductService;
import com.example.EcommerceSpring.services.ProductSyncService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductSyncService productSyncService;
    private final ProductMapper productMapper;

    public ProductController(
            ProductService productService,
            ProductSyncService productSyncService,
            ProductMapper productMapper) {
        this.productService = productService;
        this.productSyncService = productSyncService;
        this.productMapper = productMapper;
    }

    // ========== DATABASE OPERATIONS (Controller ↔ Service ↔ Repository) ==========

    /**
     * GET all products from database (returns DTOs)
     * Flow: Controller → ProductService → Repository (Entity) → Mapper → DTO → Response
     */
    @GetMapping
    public ResponseEntity<List<FakeStoreProductResponse>> getAllProducts() {
        List<Products> entities = productService.getAllProducts();
        List<FakeStoreProductResponse> dtos = productMapper.toDTOList(entities);
        return ResponseEntity.ok(dtos);
    }

    /**
     * GET single product by ID from database (returns DTO)
     */
    @GetMapping("/{id}")
    public ResponseEntity<FakeStoreProductResponse> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(productMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET products by category
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<FakeStoreProductResponse>> getProductsByCategory(
            @PathVariable String category) {
        List<Products> entities = productService.getProductsByCategory(category);
        List<FakeStoreProductResponse> dtos = productMapper.toDTOList(entities);
        return ResponseEntity.ok(dtos);
    }

    /**
     * POST - Create new product (accepts DTO, saves as Entity)
     * Flow: DTO → Mapper → Entity → Service → Repository
     */
    @PostMapping
    public ResponseEntity<FakeStoreProductResponse> createProduct(
            @RequestBody FakeStoreProductResponse productDto) {
        Products entity = productMapper.toEntity(productDto);
        Products savedEntity = productService.saveProduct(entity);
        FakeStoreProductResponse responseDto = productMapper.toDTO(savedEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    /**
     * PUT - Update existing product
     */
    @PutMapping("/{id}")
    public ResponseEntity<FakeStoreProductResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody FakeStoreProductResponse productDto) {
        try {
            Products updatedEntity = productMapper.toEntity(productDto);
            Products savedEntity = productService.updateProduct(id, updatedEntity);
            FakeStoreProductResponse responseDto = productMapper.toDTO(savedEntity);
            return ResponseEntity.ok(responseDto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE - Remove product
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.productExists(id)) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * GET product count
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getProductCount() {
        return ResponseEntity.ok(productService.getProductCount());
    }

    // ========== FAKESTORE API SYNC OPERATIONS ==========

    /**
     * POST - Sync all products from FakeStore API to database
     */
    @PostMapping("/sync")
    public ResponseEntity<String> syncAllProducts() {
        try {
            List<Products> synced = productSyncService.syncAllProductsFromFakeStore();
            return ResponseEntity.ok(
                    "Successfully synced " + synced.size() + " products from FakeStore API to database"
            );
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to sync products: " + e.getMessage());
        }
    }

    /**
     * POST - Sync single product from FakeStore API
     */
    @PostMapping("/sync/{fakeStoreId}")
    public ResponseEntity<String> syncSingleProduct(@PathVariable Long fakeStoreId) {
        try {
            Products synced = productSyncService.syncSingleProduct(fakeStoreId);
            return ResponseEntity.ok(
                    "Successfully synced product: " + synced.getTitle()
            );
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to sync product: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<FakeStoreProductResponse> getProductWithCategory(@PathVariable Long id) throws Exception {
        FakeStoreProductResponse dto = productService.getProductWithCategory(id);
        return ResponseEntity.ok(dto);
    }
}