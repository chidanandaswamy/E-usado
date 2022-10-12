package com.stackroute.productservice.service;

import com.stackroute.productservice.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD
=======
import java.math.BigDecimal;
import java.util.List;
>>>>>>> 9338046489c5cb12b492b6a216f67fc61f6f708a
import java.util.UUID;

public interface ProductService {
    ResponseEntity<String> createProduct(String product, MultipartFile image1);
    ResponseEntity<?> getProducts(int pageNumber,
                                  int pageSize,
                                  String productBrand,
                                  String productCategory,
                                  String productManufacturedYear,
                                  String warrantyStatus,
                                  BigDecimal productPrice,
                                  Float productDiscount,
                                  Float productDamageLevel,
                                  String location);
    ResponseEntity<Product> getProductById(UUID id);
    ResponseEntity<String> updateProductById(UUID id, String productAsJSONString, MultipartFile image1);
    ResponseEntity<String> deleteProductById(UUID id);
    ResponseEntity<?> getProductsByOwnerEmail(String ownerEmail, int pageNumber, int pageSize, String productBrand);
}
