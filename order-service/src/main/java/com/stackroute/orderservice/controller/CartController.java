package com.stackroute.orderservice.controller;


import com.stackroute.orderservice.model.Cart;
import com.stackroute.orderservice.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CartController {

    @Autowired
    public CartServiceImpl cartServiceImpl;

    @PostMapping("/addToCart")
    public ResponseEntity<String> createCart(@RequestBody Cart cart) {
        cart.setCartId(cartServiceImpl.getSequenceNumber(Cart.SEQUENCE_NAME));
        return cartServiceImpl.createCart(cart);
    }
    @GetMapping("/getCart/{cartId}")
    public ResponseEntity<?> getCartById(@PathVariable long cartId) {
        return cartServiceImpl.getCartById(cartId) ;
    }
    @DeleteMapping("/deleteCart/{cartId}")
    public void deleteCartById(@PathVariable long cartId) {
         cartServiceImpl.deleteCartById(cartId);
    }

}
