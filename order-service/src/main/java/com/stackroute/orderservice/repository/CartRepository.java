package com.stackroute.orderservice.repository;


import com.stackroute.orderservice.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepository extends MongoRepository<Cart,Long> {


    @Query("{'cartId' : ?0}")
    Cart deleteByCartId(long cartId);

    @Query("{'cartId' : ?0}")
    Optional<Cart> findByCartId(long cartId);

//    @Query("{'productName' : ?0}")
//    void deleteByProductName(String productName);
}
