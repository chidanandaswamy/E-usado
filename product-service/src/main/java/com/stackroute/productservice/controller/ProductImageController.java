package com.stackroute.productservice.controller;

import com.stackroute.productservice.service.ProductImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/product-service")
public class ProductImageController {

    @Autowired
    private ProductImageServiceImpl productImageService;

    @RequestMapping(path = "/product-image", method = RequestMethod.POST)
    public ResponseEntity<?> addProductImage(@RequestBody MultipartFile file){
        return productImageService.addProductImage(file);
    }
}
