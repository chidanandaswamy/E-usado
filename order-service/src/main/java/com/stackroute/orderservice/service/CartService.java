package com.stackroute.orderservice.service;


import com.stackroute.orderservice.model.Cart;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CartService {

    Cart createCart(Cart cart);




    List<Cart> getCartById(String cartOwnerEmail);
    String deleteCartById(String cartOwnerEmail);


}
