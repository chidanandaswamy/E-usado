package com.stackroute.productservice.controller;

import com.stackroute.productservice.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-service")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @RequestMapping(value = "/product", method= RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestParam String productAsJSONString, @RequestParam MultipartFile[] images){
        return productServiceImpl.createProduct(productAsJSONString, images);
    }

    @RequestMapping(value = "/products", method= RequestMethod.GET)
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

    @RequestMapping(value = "/product/{id}", method= RequestMethod.GET)
    public ResponseEntity<?> getProductById(@PathVariable UUID id){
        return productServiceImpl.getProductById(id);
    }

    @RequestMapping(value = "/products/{ownerEmail}", method= RequestMethod.GET)
    public ResponseEntity<?> getProductsByOwnerEmail(@PathVariable String ownerEmail,
                                                     @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                     @RequestParam(name = "productAddedTime", defaultValue = "0") Long productAddedTime){
        return productServiceImpl.getProductsByOwnerEmail(ownerEmail, pageNumber, pageSize, productAddedTime);
    }

    @RequestMapping(value = "/product/{id}", method= RequestMethod.PUT)
    public ResponseEntity<?> updateProductById(@PathVariable UUID id, @RequestParam String productAsJSONString, @RequestParam MultipartFile[] images){
        return productServiceImpl.updateProductById(id, productAsJSONString, images);
    }

    @RequestMapping(value = "/product/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductById(@PathVariable UUID id){
        return productServiceImpl.deleteProductById(id);
    }

    @RequestMapping(value = "/product", method= RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllProducts(){
        return productServiceImpl.deleteAllProducts();
    }

    @RequestMapping(value = "/test", method= RequestMethod.GET,
            params = {"pageNumber", "pageSize", "productBrand"})
    public void test(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                     @RequestParam(name = "pageNumber", defaultValue = "none") String search){

    }
}
