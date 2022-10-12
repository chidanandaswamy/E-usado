package com.stackroute.orderservice.service;


import com.stackroute.orderservice.model.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    void createCart(Cart cart);


    Cart getCartById(long cartId);






    boolean deleteCartById(long cartId);
}
