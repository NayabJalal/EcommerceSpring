package com.example.EcommerceSpring.controllers;

import com.example.EcommerceSpring.dto.CategoryDTO;
import com.example.EcommerceSpring.entity.Category;
import com.example.EcommerceSpring.mappers.CategoryMapper;
import com.example.EcommerceSpring.services.CategoryService;
import com.example.EcommerceSpring.services.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private final CategoryService dbCategoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(
            ICategoryService categoryService,
            CategoryService dbCategoryService,
            CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.dbCategoryService = dbCategoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/fakestore")
    public ResponseEntity<List<CategoryDTO>> getFakeStoreCategories() throws IOException {
        List<CategoryDTO> result = this.categoryService.getCategoriesService();
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(
            @RequestParam(required = false) String name) {

        if (name != null && !name.trim().isEmpty()) {
            return dbCategoryService.getCategoryByName(name)
                    .map(categoryMapper::toDTO)
                    .map(dto -> ResponseEntity.ok(List.of(dto)))
                    .orElse(ResponseEntity.ok(List.of()));
        }

        List<Category> categories = dbCategoryService.getAllCategories();
        List<CategoryDTO> dtos = categoryMapper.toDTOListFromEntities(categories);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return dbCategoryService.getCategoryById(id)
                .map(categoryMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        Category saved = dbCategoryService.saveCategory(category);
        return ResponseEntity.ok(categoryMapper.toDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (dbCategoryService.getCategoryById(id).isPresent()) {
            dbCategoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}