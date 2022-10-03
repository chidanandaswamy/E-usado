package com.stackroute.productservice.service;

import com.fasterxml.uuid.Generators;
import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Boolean createProduct(Product product) {
        product.setId(Generators.timeBasedGenerator().generate());
        Product savedProduct = productRepository.save(product);
        if(savedProduct != null && savedProduct.getId() != null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(UUID id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Boolean updateProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        if(savedProduct != null && savedProduct.getId() != null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean deleteProductById(UUID id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Product> getProductsByOwnerEmail(String ownerEmail) {
        return productRepository.findProductsByOwnerEmail(ownerEmail);
    }


}
