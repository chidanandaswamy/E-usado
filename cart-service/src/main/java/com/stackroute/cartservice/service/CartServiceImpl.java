package com.stackroute.cartservice.service;

import com.stackroute.cartservice.exception.CartNotFoundException;
import com.stackroute.cartservice.model.Cart;
import com.stackroute.cartservice.model.DbSequence;

import com.stackroute.cartservice.repository.CartRepository;
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
        Cart carted= cartRepository.save(cart);
        if(carted != null ){
            return new ResponseEntity<>("Cart is added successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Cart Creation terminated.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Cart> getProductById(long id) {
        Optional<Cart> product = cartRepository.findById(id);
        if(product.isPresent()){
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            throw new CartNotFoundException("Product with id " + id + " is not found.");
        }
    }

    @Override
    public ResponseEntity<String> deleteById(long id) {
        Optional<Cart> carted = cartRepository.findById(id);
        if(carted.isPresent()){
            cartRepository.deleteById(id);
            return new ResponseEntity<>("Cart with id " + id + " deleted successfully", HttpStatus.OK);
        } else {
            throw new CartNotFoundException("Cart with id " + id + " is not found.");
        }
    }
}
