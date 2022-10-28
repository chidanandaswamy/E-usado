package com.stackroute.productservice.service;

import com.stackroute.productservice.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    ResponseEntity<Product> getProductById(String id);
    ResponseEntity<String> updateProductById(String id, String productAsJSONString, MultipartFile[] images);
    ResponseEntity<String> deleteProductById(String id);
    ResponseEntity<String> deleteAllProducts();
    ResponseEntity<?> getProductsByOwnerEmail(String ownerEmail, int pageNumber, int pageSize, Long productAddedTime);
    ResponseEntity<?> addProductToCart(String id, String email);
}
