package com.stackroute.orderservice.service;


import com.stackroute.orderservice.model.Cart;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CartService {

    Cart createCart(Cart cart, Map<String, Object> model);


    Cart getCartById(long cartId);






    boolean deleteCartById(long cartId);
}
