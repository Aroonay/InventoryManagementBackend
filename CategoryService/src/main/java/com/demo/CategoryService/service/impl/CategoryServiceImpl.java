package com.demo.CategoryService.service.impl;




import com.demo.CategoryService.DTO.CategoryResponseDTO;
import com.demo.CategoryService.entity.Category;
import com.demo.CategoryService.repositories.CategoryRepository;
import com.app.common.utils.IdGenerator;

import com.demo.CategoryService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;



    @Override
    public Category createCategory(Category category) {
        // Check if category already exists by name
        Optional<Category> existingCategory=categoryRepository.findByName(category.getName());
        if (existingCategory.isPresent()) {
            throw new IllegalArgumentException("Category already exists with name: " + category.getName());
        }


        String randomCategoryId = String.valueOf(IdGenerator.generateSecureRandom8DigitId());
        category.setCategoryId(Long.valueOf(randomCategoryId));

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->  new NoSuchElementException("Category not found with id: " + id));
    }


    @Override
    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = getCategoryById(id);
        category.setName(categoryDetails.getName());
        category.setDescription(categoryDetails.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public Category patchCategory(Long id, Map<String, Object> updates) {
        Category category = getCategoryById(id); // throws exception if not found

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    category.setName((String) value);
                    break;
                case "description":
                    category.setDescription((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field " + key + " not recognized");
            }
        });

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }

    @Override
    public CategoryResponseDTO findByCategoryName(String categoryName) {
        Category category=categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new NoSuchElementException("Category not found with name: " + categoryName));

        return new CategoryResponseDTO(category.getCategoryId(), category.getName());
    }
}
