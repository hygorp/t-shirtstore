package com.hygorp.backendspring.repositories.category;

import com.hygorp.backendspring.models.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
