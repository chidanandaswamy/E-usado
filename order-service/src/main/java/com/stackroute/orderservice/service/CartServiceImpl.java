package com.stackroute.orderservice.service;


//import com.stackroute.orderservice.config.CartProducer;
//import com.stackroute.orderservice.dto.CartDto;
import com.stackroute.orderservice.exception.CartNotFoundException;
import com.stackroute.orderservice.model.Cart;
import com.stackroute.orderservice.model.DbSequence;
import com.stackroute.orderservice.repository.CartRepository;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.stereotype.Service;


import java.util.Objects;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    public CartRepository cartRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private RabbitTemplate rabbitTemplate;



//    public String getSequenceNumber(String seqName){
//        Query qry=new Query(Criteria.where("id").is(seqName));
//        Update update=new Update().inc("seq",1);
//        DbSequence counter = mongoOperations
//                .findAndModify(qry,update,FindAndModifyOptions.options().returnNew(true).upsert(true),DbSequence.class);
//        return !Objects.isNull(counter)?counter.getSeq():String.valueOf(1);
//    }

    @Override
    public Cart createCart(Cart cart) {

        Cart cart1= cartRepository.save(cart);
        if(cart1 != null ){
//            rabbitTemplate.convertAndSend("Order_exchange","Order_routing key",cart);
            System.out.println("Cart is added successfully.");
        } else {


            System.out.println("Cart Creation terminated.");
        }
        return cart;
    }

    @Override
    public Cart  getCartById(String productId) {
        Cart carts = cartRepository.findByCartId(productId);
        if (carts != null ) {
            return carts;

        }
        else{
            throw new CartNotFoundException("user email doesn't exist");
        }
    }

    @Override
    public boolean deleteCartById(String productId) {
        Cart carted = cartRepository.findByCartId(productId);
        if(carted != null){
            cartRepository.deleteByCartId(productId);

            return true;
        } else {
            throw new CartNotFoundException("Cart with id " + productId + " is not found.");
        }
    }
}
