package com.shahriar.Library.service;

import com.shahriar.Library.entity.Category;
import com.shahriar.Library.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found"));
    }
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found"));
        categoryRepository.deleteById(category.getId());
    }
}
