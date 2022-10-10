package com.stackroute.productservice.repository;

import com.stackroute.productservice.model.ProductImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductImageRepository extends MongoRepository<ProductImage, UUID> {

}
