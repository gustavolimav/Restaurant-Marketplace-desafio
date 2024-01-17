package com.gustavolima.marketplace.controllers;

import com.gustavolima.marketplace.controllers.DTOs.CategoryDTO;
import com.gustavolima.marketplace.domain.category.Category;
import com.gustavolima.marketplace.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService _categoryService;

    public CategoryController(CategoryService categoryService) {
        _categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> postCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = _categoryService.postCategory(categoryDTO);

        return ResponseEntity.ok().body(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = _categoryService.getAllCategories();

        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> putCategory(@PathVariable("id") String categoryId, @RequestBody CategoryDTO categoryDTO) {
        Category category = _categoryService.updateCategory(categoryId, categoryDTO);

        return ResponseEntity.ok().body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") String categoryId) {
        _categoryService.deleteCategory(categoryId);

        return ResponseEntity.noContent().build();
    }

}
