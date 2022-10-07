package com.stackroute.cartservice.controller;

import com.stackroute.cartservice.model.Cart;
import com.stackroute.cartservice.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CartController {

    @Autowired
    public CartServiceImpl cartServiceImpl;

    @PostMapping("/addCart")
    public ResponseEntity<String> createCart(Cart cart) {
        cart.setId(cartServiceImpl.getSequenceNumber(cart.SEQUENCE_NAME));
        return cartServiceImpl.createCart(cart);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Cart> getProductById(long id) {
        return cartServiceImpl.getProductById(id) ;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(long id) {
        return cartServiceImpl.deleteById(id);
    }

}
