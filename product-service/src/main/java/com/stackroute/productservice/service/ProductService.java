package com.stackroute.productservice.service;

import com.stackroute.productservice.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    public Boolean createProduct(Product product);
    public List<Product> getProducts();
    public Product getProductById(UUID id);
    public Boolean updateProduct(Product product);
    public Boolean deleteProductById(UUID id);
    public List<Product> getProductsByOwnerEmail(String ownerEmail);
}
