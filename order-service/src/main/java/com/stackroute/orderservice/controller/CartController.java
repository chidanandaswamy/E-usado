package com.stackroute.orderservice.controller;


import com.stackroute.orderservice.config.Consumer;
import com.stackroute.orderservice.model.Cart;
import com.stackroute.orderservice.service.CartServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CartController {

    @Autowired
    private Consumer consumer;

    private ResponseEntity responseEntity;
    @Autowired
    public CartServiceImpl cartServiceImpl;

    @PostMapping("/addToCart")
    public void createCart(@RequestBody Cart cart) {
        cart.setCartId(cartServiceImpl.getSequenceNumber(Cart.SEQUENCE_NAME));
        cartServiceImpl.createCart(cart);
    }
    @GetMapping("/getCart/{cartId}")
    public ResponseEntity<?> getCartById(@PathVariable long cartId) {
        responseEntity= new ResponseEntity<>(cartServiceImpl.getCartById(cartId), HttpStatus.CREATED);

        return responseEntity;
    }


    @DeleteMapping("/deleteCart/{cartId}")
    public ResponseEntity<?> deleteCartById(@PathVariable long cartId) {
        responseEntity=new ResponseEntity<Boolean>(cartServiceImpl.deleteCartById(cartId), HttpStatus.OK);
        return responseEntity;
    }

}
