import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class CategoryController {

    private final DbCategoryService dbCategoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(DbCategoryService dbCategoryService, CategoryMapper categoryMapper) {
        this.dbCategoryService = dbCategoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getAllCategories(
            @RequestParam(required = false) String name,
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {

        Page<Category> categories;

        if (name != null && !name.trim().isEmpty()) {
            // For filtering by name, since it's unique, return single or empty
            return dbCategoryService.getCategoryByName(name)
                    .map(categoryMapper::toDTO)
                    .map(dto -> ResponseEntity.ok(Page.empty(pageable).withContent(List.of(dto))))
                    .orElse(ResponseEntity.ok(Page.empty(pageable)));
        } else {
            categories = dbCategoryService.getAllCategories(pageable);
        }

        Page<CategoryDTO> dtos = categories.map(categoryMapper::toDTO);
        return ResponseEntity.ok(dtos);
    }
}
