package pe.isil.demo.productsapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import pe.isil.demo.productsapi.model.Product;
import pe.isil.demo.productsapi.service.ProductService;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Endpoints for managing products")

public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Find all products", description = "Returns a list of all products")
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = service.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find product by ID", description = "Returns a product with the specified ID")
    public ResponseEntity<Product> findById(String id) {
        Product product = service.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/search")
    @Operation(summary = "Find product by name", description = "Returns a list of products with the specified name")
    public ResponseEntity<List<Product>> findByName(String name) {
        List<Product> products = service.findByName(name);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Product created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid product data")
    })
    public ResponseEntity<Product> create(
            @Valid @RequestBody Product product) {
        Product createdProduct = service.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product", description = "Updates the details of an existing product with the specified ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product updated successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Product> update(@PathVariable String id,
            @Valid @RequestBody Product product) {
        Product updatedProduct = service.update(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Deletes the product with the specified ID")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
