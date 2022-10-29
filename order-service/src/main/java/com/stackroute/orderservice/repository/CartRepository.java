package com.stackroute.orderservice.repository;


import com.stackroute.orderservice.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;




@Repository
public interface CartRepository extends MongoRepository<Cart,String> {


    @Query(value = "{'productId' : ?0}",delete = true)
    Cart deleteByCartId(String productId);

    @Query("{'productId' : ?0}")
    Cart findByCartId(String productId);


}
