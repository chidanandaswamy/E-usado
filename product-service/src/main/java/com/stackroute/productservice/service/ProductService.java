package com.stackroute.productservice.service;

import com.stackroute.productservice.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ResponseEntity<String> createProduct(Product product);
    ResponseEntity<?> getProducts(int pageNumber, int pageSize, String productBrand);
    ResponseEntity<Product> getProductById(UUID id);
    ResponseEntity<String> updateProductById(UUID id, Product product);
    ResponseEntity<String> deleteProductById(UUID id);
    ResponseEntity<?> getProductsByOwnerEmail(String ownerEmail, int pageNumber, int pageSize, String productBrand);
}
