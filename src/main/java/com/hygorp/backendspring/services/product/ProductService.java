package com.hygorp.backendspring.services.product;

import com.hygorp.backendspring.models.product.Product;
import com.hygorp.backendspring.models.product.ProductDTO;
import com.hygorp.backendspring.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product updateProduct(UUID id, ProductDTO product) {
        Product returnedProduct = productRepository.findById(id).orElseThrow();
        if(product.name() != null) {
            returnedProduct.setName(product.name());
        }
        if(product.description() != null) {
            returnedProduct.setDescription(product.description());
        }
        if(product.price() != null) {
            returnedProduct.setPrice(product.price());
        }
        if(product.color() != null) {
            returnedProduct.setColor(product.color());
        }
        if(product.size() != null) {
            returnedProduct.setSize(product.size());
        }
        if(product.genre() != null) {
            returnedProduct.setGenre(product.genre());
        }
        if(product.categories() != null) {
            returnedProduct.setCategories(product.categories());
        }

        return productRepository.save(returnedProduct);
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
