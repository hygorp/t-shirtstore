package com.hygorp.backendspring.services.category;

import com.hygorp.backendspring.models.category.Category;
import com.hygorp.backendspring.models.category.CategoryDTO;
import com.hygorp.backendspring.repositories.category.CategoryRepository;
import com.hygorp.backendspring.resources.category.CategoryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        for(Category category : categories) {
            category.add(linkTo(methodOn(CategoryResource.class).getCategoryById(category.getId())).withSelfRel());
        }
        return categories;
    }

    public Category getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.add(linkTo(methodOn(CategoryResource.class).getAllCategories()).withRel("Category List"));
        return category;
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
