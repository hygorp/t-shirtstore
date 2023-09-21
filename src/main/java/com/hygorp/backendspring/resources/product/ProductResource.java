package com.hygorp.backendspring.resources.product;

import com.hygorp.backendspring.models.product.Product;
import com.hygorp.backendspring.models.product.ProductDTO;
import com.hygorp.backendspring.services.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    ProductService productService;

    @PostMapping
    @Operation(summary = "Save Product", description = "Must persist in database a new Product",
            tags = {"Products"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = Product.class))
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
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
    @Operation(summary = "Return All Products", description = "Must return all Products persisted in database",
            tags = {"Products"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Product.class))
                                    )
                            }
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        for(Product product : products) {
            UUID id = product.getId();
            product.add(linkTo(methodOn(ProductResource.class).getProductById(id)).withSelfRel());
        }
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Return Product by ID", description = "Must return Product matched by passed ID",
            tags = {"Products"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Product.class))
                    ),

                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        Product product = productService.getProductById(id);
        product.add(linkTo(methodOn(ProductResource.class).getAllProducts()).withRel("Product List"));
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit Product", description = "Must save changes sent to the Product selected by ID",
            tags = {"Products"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Product.class))
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody ProductDTO product) {
        Product returnedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok().body(returnedProduct);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Product", description = "Must delete the Product corresponding to the passed id",
            tags = {"Products"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
