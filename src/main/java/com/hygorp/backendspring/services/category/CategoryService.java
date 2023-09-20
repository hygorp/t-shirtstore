package com.hygorp.backendspring.services.category;

import com.hygorp.backendspring.models.category.Category;
import com.hygorp.backendspring.models.category.CategoryDTO;
import com.hygorp.backendspring.repositories.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public Category updateCategory(Long id, CategoryDTO category) {
        Category returnedCategory = categoryRepository.findById(id).orElseThrow();
        returnedCategory.setName(category.name());
        return categoryRepository.save(returnedCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
