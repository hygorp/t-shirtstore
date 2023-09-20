package com.hygorp.backendspring.models.product;

import com.hygorp.backendspring.models.category.Category;
import com.hygorp.backendspring.models.product.enums.ProductColorEnum;
import com.hygorp.backendspring.models.product.enums.ProductGenreEnum;
import com.hygorp.backendspring.models.product.enums.ProductSizeEnum;

import java.util.Set;

public record ProductDTO(
        String name,
        String description,
        Double price,
        ProductColorEnum color,
        ProductSizeEnum size,
        ProductGenreEnum genre,
        Set<Category> categories) {
}
