package com.stackroute.productservice.controller;

import com.stackroute.productservice.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestParam String productAsJSONString, @RequestParam MultipartFile[] images){
        return productServiceImpl.createProduct(productAsJSONString, images);
    }

    @PostMapping("/product/add-to-cart/product-id/{id}/user-email/{email}")
    public ResponseEntity<?> addProductToCart(@PathVariable String id, @PathVariable String email){
        return productServiceImpl.addProductToCart(id, email);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(@RequestParam(name = "search", defaultValue = "none") String search,
                                         @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                         @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                         @RequestParam(name = "productBrand", defaultValue = "all") String productBrand,
                                         @RequestParam(name = "productCategory", defaultValue = "all") String productCategory,
                                         @RequestParam(name = "productManufacturedYear", defaultValue = "all") String productManufacturedYear,
                                         @RequestParam(name = "warrantyStatus", defaultValue = "all") String warrantyStatus,
                                         @RequestParam(name = "productPrice", defaultValue = "-1") Double productPrice,
                                         @RequestParam(name = "productDiscount", defaultValue = "-1") Float productDiscount,
                                         @RequestParam(name = "productDamageLevel", defaultValue = "-1") Float productDamageLevel,
                                         @RequestParam(name = "location", defaultValue = "all") String location,
                                         @RequestParam(name = "productAvailability", defaultValue = "all") String productAvailability){
        return productServiceImpl.getProducts(search,
                pageNumber,
                pageSize,
                productBrand,
                productCategory,
                productManufacturedYear,
                warrantyStatus,
                productPrice,
                productDiscount,
                productDamageLevel,
                location,
                productAvailability);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id){
        return productServiceImpl.getProductById(id);
    }

    @GetMapping("/products/{ownerEmail}")
    public ResponseEntity<?> getProductsByOwnerEmail(@PathVariable String ownerEmail,
                                                     @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                     @RequestParam(name = "productAddedTime", defaultValue = "0") Long productAddedTime){
        return productServiceImpl.getProductsByOwnerEmail(ownerEmail, pageNumber, pageSize, productAddedTime);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable String id, @RequestParam String productAsJSONString, @RequestParam MultipartFile[] images){
        return productServiceImpl.updateProductById(id, productAsJSONString, images);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable String id){
        return productServiceImpl.deleteProductById(id);
    }

    @DeleteMapping("/product")
    public ResponseEntity<?> deleteAllProducts(){
        return productServiceImpl.deleteAllProducts();
    }

}
