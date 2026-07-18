package pe.isil.demo.productsapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pe.isil.demo.productsapi.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByNameContainingIgnoreCase(String name);
}
