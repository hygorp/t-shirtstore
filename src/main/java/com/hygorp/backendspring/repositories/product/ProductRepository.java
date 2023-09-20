package com.hygorp.backendspring.repositories.product;

import com.hygorp.backendspring.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
