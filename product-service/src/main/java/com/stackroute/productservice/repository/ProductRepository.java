package com.stackroute.productservice.repository;

import com.stackroute.productservice.model.Product;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, UUID> {

    @Query(value="{productOwnerEmail:'?0'}")
    List<Product> findProductsByOwnerEmail(String email);

    @Query()
    @Aggregation(pipeline = {
            "{ '$skip' : ?0 }",
            "{ '$limit' : ?1 }"
    })
    List<Product> findProducts(int offset, int limit);
}
