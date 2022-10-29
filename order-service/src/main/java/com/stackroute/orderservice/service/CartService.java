package com.stackroute.orderservice.service;


import com.stackroute.orderservice.model.Cart;

import org.springframework.stereotype.Service;




@Service
public interface CartService {

    Cart createCart(Cart cart);




    Cart getCartById(String productId);
boolean deleteCartById(String productId);


}
