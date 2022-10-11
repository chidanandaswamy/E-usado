package com.stackroute.orderservice.service;


import com.stackroute.orderservice.model.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    ResponseEntity<String> createCart(Cart cart);


    ResponseEntity<Cart> getCartById(long cartId);



    ResponseEntity<String> deleteCartById(long cartId);
}
