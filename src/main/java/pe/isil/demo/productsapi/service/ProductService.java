package pe.isil.demo.productsapi.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import pe.isil.demo.productsapi.model.Product;
import pe.isil.demo.productsapi.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public List<Product> findByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Product create(Product product) {
        product.setId(null);
        return repository.save(product);
    }

    public Product update(String id, Product product) {
        Product existingProduct = findById(id);
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setActive(product.isActive());
        return repository.save(existingProduct);
    }

    public void delete(String id) {
        Product existingProduct = findById(id);
        repository.delete(existingProduct);
    }
}
