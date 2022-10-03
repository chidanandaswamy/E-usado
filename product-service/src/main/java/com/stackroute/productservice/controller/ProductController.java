package com.stackroute.productservice.controller;

import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-service")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @RequestMapping(value = "/product", method= RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        return new ResponseEntity<Boolean>(productServiceImpl.createProduct(product), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/products", method= RequestMethod.GET)
    public ResponseEntity<?> getProducts(){
        return new ResponseEntity<List<Product>>(productServiceImpl.getProducts(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/{id}", method= RequestMethod.GET)
    public ResponseEntity<?> getProductById(@PathVariable UUID id){
        return new ResponseEntity<Product>(productServiceImpl.getProductById(id), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/products/{ownerEmail}", method= RequestMethod.GET)
    public ResponseEntity<?> getProductsByOwnerEmail(@PathVariable String ownerEmail){
        return new ResponseEntity<List<Product>>(productServiceImpl.getProductsByOwnerEmail(ownerEmail), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product", method= RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@RequestBody Product product){
        return new ResponseEntity<Boolean>(productServiceImpl.updateProduct(product), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductById(@PathVariable UUID id){
        return new ResponseEntity<Boolean>(productServiceImpl.deleteProductById(id), HttpStatus.CREATED);
    }
}
