package com.stackroute.orderservice.repository;


import com.stackroute.orderservice.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;




@Repository
public interface CartRepository extends MongoRepository<Cart,Long> {


    @Query(value = "{'cartId' : ?0}",delete = true)
    Cart deleteByCartId(long cartId);

    @Query("{'cartId' : ?0}")
    Cart findByCartId(long cartId);


}
