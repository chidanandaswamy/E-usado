package com.stackroute.productservice.service;

import com.stackroute.productservice.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    ResponseEntity<String> createProduct(String product, MultipartFile[] images);
    ResponseEntity<?> getProducts(String search,
                                  int pageNumber,
                                  int pageSize,
                                  String productBrand,
                                  String productCategory,
                                  String productManufacturedYear,
                                  String warrantyStatus,
                                  Double productPrice,
                                  Float productDiscount,
                                  Float productDamageLevel,
                                  String location,
                                  String productAvailability);
    ResponseEntity<Product> getProductById(UUID id);
    ResponseEntity<String> updateProductById(UUID id, String productAsJSONString, MultipartFile[] images);
    ResponseEntity<String> deleteProductById(UUID id);
    ResponseEntity<String> deleteAllProducts();
    ResponseEntity<?> getProductsByOwnerEmail(String ownerEmail, int pageNumber, int pageSize, Long productAddedTime);
}
