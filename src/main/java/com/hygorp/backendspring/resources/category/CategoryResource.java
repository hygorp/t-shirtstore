package com.hygorp.backendspring.resources.category;

import com.hygorp.backendspring.models.category.Category;
import com.hygorp.backendspring.models.category.CategoryDTO;
import com.hygorp.backendspring.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody CategoryDTO category) {
        Category newCategory = new Category(null, category.name());
        categoryService.saveCategory(newCategory);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCategory.getId()).toUri();
        return ResponseEntity.created(uri).body(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO category) {
        Category returnedCategory = categoryService.updateCategory(id, category);
        return ResponseEntity.ok().body(returnedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
