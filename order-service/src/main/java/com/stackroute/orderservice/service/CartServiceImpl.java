package com.stackroute.orderservice.service;


import com.stackroute.orderservice.exception.CartNotFoundException;
import com.stackroute.orderservice.model.Cart;
import com.stackroute.orderservice.model.DbSequence;
import com.stackroute.orderservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
@Service
public class CartServiceImpl implements CartService{

    @Autowired
    public CartRepository cartRepository;

    @Autowired
    private MongoOperations mongoOperations;

    public int getSequenceNumber(String sequenceName){
        Query query=new Query(Criteria.where("id").is(sequenceName));
        Update update=new Update().inc("seq", 1);
        DbSequence counter = mongoOperations.findAndModify(query,update, FindAndModifyOptions.options().returnNew(true).upsert(true),DbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq():1;
    }

    @Override
    public ResponseEntity<String> createCart(Cart cart) {
        Cart cart1= cartRepository.save(cart);
        if(cart1 != null ){
            return new ResponseEntity<>("Cart is added successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Cart Creation terminated.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Cart>  getCartById(long cartId) {
        Optional<Cart> orders = cartRepository.findByCartId(cartId);
        if(orders.isPresent()){
            return new ResponseEntity<>(orders.get(), HttpStatus.OK);
        } else {
            throw new CartNotFoundException("Cart with id " + cartId + " is not found.");
        }
    }

    @Override
    public ResponseEntity<String> deleteCartById(long cartId) {
        Optional<Cart> carted = cartRepository.findByCartId(cartId);
        if(carted.isPresent()){
            cartRepository.deleteByCartId(cartId);

            return new ResponseEntity<>("Cart with id " + cartId + " deleted successfully", HttpStatus.OK);
        } else {
            throw new CartNotFoundException("Cart with id " + cartId + " is not found.");
        }
    }
}
