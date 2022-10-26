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

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    public Cart createCart(Cart cart, Map<String, Object> model) {
//        Map<String, Object> model = new HashMap<>();
        model.put("name", cart.getProductName());
        model.put("location", "Banglore, India");
        model.put("productPrice", cart.getProductPrice());
        model.put("productName", cart.getProductDescription());
        model.put("productBrand", cart.getProductBrand());
        model.put("productDiscount", cart.getProductDiscount());
        model.put("productManufacturedYear", cart.getProductManufacturedYear());
        model.put("DamageLevel", cart.getProductDamageLevel());
        model.put("productSpecs", cart.getProductSpecs());

        Cart cart1= cartRepository.save(cart);
        if(cart1 != null ){
            System.out.println("Cart is added successfully.");
        } else {
            System.out.println("Cart Creation terminated.");
        }
        return cart;
    }

    @Override
    public Cart  getCartById(long cartId) {
        Cart carts = cartRepository.findByCartId(cartId);
        if (carts != null ) {
            return carts;

        }
        else{
            throw new CartNotFoundException("user email doesn't exist");
        }
    }

    @Override
    public boolean deleteCartById(long cartId) {
        Cart carted = cartRepository.findByCartId(cartId);
        if(carted != null){
            cartRepository.deleteByCartId(cartId);

            return true;
        } else {
            throw new CartNotFoundException("Cart with id " + cartId + " is not found.");
        }
    }
}
