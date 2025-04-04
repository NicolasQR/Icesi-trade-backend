package com.example.icesitrade.service;

import com.example.icesitrade.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category updatedCategory);
    void deleteCategory(Long id);
}
