package com.example.EcommerceSpring.controllers;

import com.example.EcommerceSpring.dto.ProductDTO;
import com.example.EcommerceSpring.dto.ProductWithCategoryDTO;
import com.example.EcommerceSpring.entity.Products;
import com.example.EcommerceSpring.mappers.ProductMapper;
import com.example.EcommerceSpring.services.IProductSyncService;
import com.example.EcommerceSpring.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;
    private final IProductSyncService productSyncService;
    private final ProductMapper productMapper;
    /**
     * GET all products OR filter by category using query parameter
     * Supports pagination with page, size, sort parameters
     * Examples:
     *   GET /api/products?page=0&size=10&sort=title,asc              → Returns paginated products
     *   GET /api/products?category=hoodie&page=0&size=5 → Returns paginated products in "hoodie" category
     */
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getAllProducts(
            @RequestParam(required = false) String category,
            @PageableDefault(size = 10, sort = "title") Pageable pageable) {

        Page<Products> entities = productService.getAllProducts(category, pageable);
        Page<ProductDTO> dtos = entities.map(productMapper::toDTO);
        return ResponseEntity.ok(dtos);
    }

    /**
     * GET single product by ID (flat structure)
     * Response: { id, title, price, categoryId, category: "name", ... }
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(productMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET product with nested category object
     * Response: { id, title, price, category: {id, name}, ... }
     */
    @GetMapping("/{id}/details")
    public ResponseEntity<ProductWithCategoryDTO> getProductWithCategoryDetails(@PathVariable Long id) {
        ProductWithCategoryDTO dto = productService.getProductWithCategory(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * GET products by category (path variable)
     * Supports pagination
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<Page<ProductDTO>> getProductsByCategory(
            @PathVariable String category,
            @PageableDefault(size = 10, sort = "title") Pageable pageable) {
        Page<Products> entities = productService.getProductsByCategory(category, pageable);
        Page<ProductDTO> dtos = entities.map(productMapper::toDTO);
        return ResponseEntity.ok(dtos);
    }

    /**
     * POST - Create new product
     */
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(
            @RequestBody ProductDTO productDto) {
        Products entity = productMapper.toEntity(productDto);
        Products savedEntity = productService.saveProduct(entity);
        ProductDTO responseDto = productMapper.toDTO(savedEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    /**
     * PUT - Update existing product
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO productDto) {
        Products updatedEntity = productMapper.toEntity(productDto);
        Products savedEntity = productService.updateProduct(id, updatedEntity);
        ProductDTO responseDto = productMapper.toDTO(savedEntity);
        return ResponseEntity.ok(responseDto);
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
}