package com.stackroute.orderservice.repository;


import com.stackroute.orderservice.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;




@Repository
public interface CartRepository extends MongoRepository<Cart,String> {


    @Query(value = "{'cartOwnerEmail' : ?0}",delete = true)
    Cart deleteByCartId(String cartOwnerEmail);

    @Query("{'cartOwnerEmail' : ?0}")
    Cart findByCartId(String cartOwnerEmail);


}
