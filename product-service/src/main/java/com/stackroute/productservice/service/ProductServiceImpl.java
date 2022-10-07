package com.stackroute.productservice.service;

import com.fasterxml.uuid.Generators;
import com.stackroute.productservice.exception.ProductNotFoundException;
import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<String> createProduct(Product product) {
        product.setId(Generators.timeBasedGenerator().generate());
        Product savedProduct = productRepository.save(product);
        if(savedProduct != null && savedProduct.getId() != null){
            return new ResponseEntity<>("Product added successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Could not create product.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getProducts(int pageNumber,
                                         int pageSize,
                                         String productBrand) {
        int offset = 0;
        int limit = 0;

        if(pageNumber == 0){
            pageNumber = 1;
        }

        if(pageSize == 0){
            pageSize = 10;
        }

        limit = pageSize;
        offset = pageSize * (pageNumber - 1);

        List<Product> products = productRepository.findProducts(offset, limit);
        if(products != null && products.size() > 0){
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No products found", HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<Product> getProductById(UUID id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
        } else {
            throw new ProductNotFoundException("Product with id " + id + " is not found.");
        }
    }

    @Override
    public ResponseEntity<String> updateProductById(UUID id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            Product savedProduct = productRepository.save(product);
            if(savedProduct != null && savedProduct.getId() != null){
                return new ResponseEntity<>("Product with id " + id + " not found", HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Update of product with id " + id + " failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new ProductNotFoundException("Product with id " + id + " is not found.");
        }
    }

    @Override
    public ResponseEntity<String> deleteProductById(UUID id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            productRepository.deleteById(id);
            return new ResponseEntity<>("Product with id " + id + " deleted successfull", HttpStatus.OK);
        } else {
            throw new ProductNotFoundException("Product with id " + id + " is not found.");
        }
    }

    @Override
    public ResponseEntity<?> getProductsByOwnerEmail(String ownerEmail,
                                                     int pageNumber,
                                                     int pageSize,
                                                     String productBrand) {
        int offset = 0;
        int limit = 0;

        if(pageNumber == 0){
            pageNumber = 1;
        }

        if(pageSize == 0){
            pageSize = 10;
        }

        limit = pageSize;
        offset = pageSize * (pageNumber - 1);

        List<Product> products = productRepository.findProductsByOwnerEmail(ownerEmail);
        if(products != null && products.size() > 0){
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("User " + ownerEmail + " has no products", HttpStatus.OK);
        }
    }


}
