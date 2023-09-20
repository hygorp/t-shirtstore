package com.hygorp.backendspring.resources.product;

import com.hygorp.backendspring.models.product.Product;
import com.hygorp.backendspring.models.product.ProductDTO;
import com.hygorp.backendspring.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody ProductDTO product) {
        Product newProduct = new Product(
                null,
                product.name(),
                product.description(),
                product.price(),
                product.color(),
                product.size(),
                product.genre(),
                product.categories()
        );

        productService.saveProduct(newProduct);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody ProductDTO product) {
        Product returnedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok().body(returnedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
