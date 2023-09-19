package com.hygorp.backendspring.models.product;

import com.hygorp.backendspring.models.category.Category;
import com.hygorp.backendspring.models.product.enums.ProductColorEnum;
import com.hygorp.backendspring.models.product.enums.ProductGenreEnum;
import com.hygorp.backendspring.models.product.enums.ProductSizeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity(name = "tb_product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    private String name;
    private String description;
    private Double price;
    private ProductColorEnum color;
    private ProductSizeEnum size;
    private ProductGenreEnum genre;

    @ManyToMany
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

}
