package com.stackroute.cartservice.service;

import com.stackroute.cartservice.model.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    ResponseEntity<String> createCart(Cart cart);
    ResponseEntity<Cart> getProductById(long id);
    ResponseEntity<String> deleteById(long id);
}
