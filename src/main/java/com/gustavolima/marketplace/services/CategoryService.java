package com.gustavolima.marketplace.services;

import com.gustavolima.marketplace.controllers.DTOs.CategoryDTO;
import com.gustavolima.marketplace.domain.category.Category;
import com.gustavolima.marketplace.domain.category.exceptions.CategoryNotFoundException;
import com.gustavolima.marketplace.repositories.CategoryRepository;
import com.gustavolima.marketplace.services.aws.AWSSNSService;
import com.gustavolima.marketplace.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository _categoryRepository;
    private final AWSSNSService _awsSNSService;

    public CategoryService(CategoryRepository categoryRepository, AWSSNSService awssnsService) {
        _categoryRepository = categoryRepository;
        _awsSNSService = awssnsService;
    }

    public Category postCategory(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO);

        _categoryRepository.save(category);

        _awsSNSService.publish(new MessageDTO(category.toString()));

        return category;
    }

    public List<Category> getAllCategories() {
        return _categoryRepository.findAll();
    }

    public Category updateCategory(String categoryId, CategoryDTO categoryDTO) {
        Category category = _categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        if (categoryDTO.title() != null) {
            category.setTitle(categoryDTO.title());
        }

        if (categoryDTO.description() != null) {
            category.setDescription(categoryDTO.description());
        }

        _categoryRepository.save(category);

        _awsSNSService.publish(new MessageDTO(category.toString()));

        return category;
    }

    public void deleteCategory(String categoryId) {
        Category category = _categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        _categoryRepository.delete(category);
    }

    public Optional<Category> getCategoryById(String categoryId) {
        return _categoryRepository.findById(categoryId);
    }

}
