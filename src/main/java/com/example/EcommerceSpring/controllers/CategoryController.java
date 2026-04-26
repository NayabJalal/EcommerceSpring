package com.example.EcommerceSpring.controllers;

import com.example.EcommerceSpring.dto.CategoryDTO;
import com.example.EcommerceSpring.entity.Category;
import com.example.EcommerceSpring.exception.CategoryNotFoundException;
import com.example.EcommerceSpring.mappers.CategoryMapper;
import com.example.EcommerceSpring.services.CategoryService;
import com.example.EcommerceSpring.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private final CategoryService dbCategoryService;
    private final CategoryMapper categoryMapper;


    @GetMapping("/fakestore")
    public ResponseEntity<List<CategoryDTO>> getFakeStoreCategories() throws IOException {
        List<CategoryDTO> result = this.categoryService.getCategoriesService();
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getAllCategories(
            @RequestParam(required = false) String name,
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {

        if (name != null && !name.trim().isEmpty()) {
            return dbCategoryService.getCategoryByName(name)
                    .map(categoryMapper::toDTO)
                    .map(dto -> ResponseEntity.ok((Page<CategoryDTO>) new PageImpl<>(List.of(dto), pageable, 1)))
                    .orElse(ResponseEntity.ok(Page.empty(pageable)));
        }

        Page<Category> categories = dbCategoryService.getAllCategories(pageable);
        Page<CategoryDTO> dtos = categories.map(categoryMapper::toDTO);
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

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        try {
            Category updatedEntity = categoryMapper.toEntity(categoryDTO);
            Category savedEntity = dbCategoryService.updateCategory(id, updatedEntity);
            CategoryDTO responseDto = categoryMapper.toDTO(savedEntity);
            return ResponseEntity.ok(responseDto);
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
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