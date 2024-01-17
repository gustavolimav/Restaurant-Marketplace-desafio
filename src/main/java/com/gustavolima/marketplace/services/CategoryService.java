package com.gustavolima.marketplace.services;

import com.gustavolima.marketplace.controllers.CategoryDTO;
import com.gustavolima.marketplace.domain.category.Category;
import com.gustavolima.marketplace.exceptions.CategoryNotFoundException;
import com.gustavolima.marketplace.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository _categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        _categoryRepository = categoryRepository;
    }

    public Category postCategory(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO);

        this._categoryRepository.save(category);

        return category;
    }

    public List<Category> getAllCategories() {
        return _categoryRepository.findAll();
    }

    public Category updateCategory(String categoryId, CategoryDTO categoryDTO) {
        Category category = _categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        if (!categoryDTO.title().isBlank()) {
            category.setTitle(categoryDTO.title());
        }

        if (!categoryDTO.description().isBlank()) {
            category.setDescription(categoryDTO.description());
        }

        _categoryRepository.save(category);

        return category;
    }

    public void deleteCategory(String categoryId) {
        Category category = _categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        _categoryRepository.delete(category);
    }

}
