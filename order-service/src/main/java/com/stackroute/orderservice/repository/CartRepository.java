package com.stackroute.orderservice.repository;


import com.stackroute.orderservice.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartRepository extends MongoRepository<Cart,String> {


    @Query(value = "{'cartOwnerEmail' : ?0}",delete = true)
    List<Cart> deleteByCartId(String cartOwnerEmail);

    @Query("{'cartOwnerEmail' : ?0}")
    List<Cart> findByCartId(String cartOwnerEmail);


}
