package com.stackroute.productservice.controller;

import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-service")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @RequestMapping(value = "/product", method= RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        return productServiceImpl.createProduct(product);
    }

    @RequestMapping(value = "/products", method= RequestMethod.GET,
            params = {"pageNumber", "pageSize", "productBrand"})
    public ResponseEntity<?> getProducts(@RequestParam int pageNumber,
                                         @RequestParam int pageSize,
                                         @RequestParam String productBrand){
        return productServiceImpl.getProducts(pageNumber, pageSize, productBrand);
    }

    @RequestMapping(value = "/product/{id}", method= RequestMethod.GET)
    public ResponseEntity<?> getProductById(@PathVariable UUID id){
        return productServiceImpl.getProductById(id);
    }

    @RequestMapping(value = "/product/{ownerEmail}", method= RequestMethod.GET,
            params = {"pageNumber", "pageSize", "productBrand"})
    public ResponseEntity<?> getProductsByOwnerEmail(@PathVariable String ownerEmail,
                                                     @RequestParam int pageNumber,
                                                     @RequestParam int pageSize,
                                                     @RequestParam String productBrand){
        return productServiceImpl.getProductsByOwnerEmail(ownerEmail, pageNumber, pageSize, productBrand);
    }

    @RequestMapping(value = "/product/{id}", method= RequestMethod.PUT)
    public ResponseEntity<?> updateProductById(@PathVariable UUID id, @RequestBody Product product){
        return productServiceImpl.updateProductById(id, product);
    }

    @RequestMapping(value = "/product/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductById(@PathVariable UUID id){
        return productServiceImpl.deleteProductById(id);
    }

}
