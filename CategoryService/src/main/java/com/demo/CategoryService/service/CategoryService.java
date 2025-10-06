package com.demo.CategoryService.service;

import com.demo.CategoryService.DTO.CategoryResponseDTO;
import com.demo.CategoryService.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    Category createCategory(Category category);

    // Retrieve all categories
    List<Category> getAllCategories();

    // Retrieve a category by ID
    Category getCategoryById(Long id);

    // Update a category completely (PUT)
    Category updateCategory(Long id, Category categoryDetails);

    // Partial update of category (PATCH)
    Category patchCategory(Long id, Map<String, Object> updates);

    // Delete a category by ID
    void deleteCategory(Long id);

    CategoryResponseDTO findByCategoryName(String categoryName);
}
