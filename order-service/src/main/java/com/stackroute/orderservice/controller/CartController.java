package com.stackroute.orderservice.controller;


import com.stackroute.orderservice.model.Cart;
import com.stackroute.orderservice.service.CartServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CartController {



    private ResponseEntity responseEntity;
    @Autowired
    public CartServiceImpl cartServiceImpl;



    @PostMapping("/addToCart")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
//        cart.setCartId(cartServiceImpl.getSequenceNumber(Cart.SEQUENCE_NAME));

        Map<String, Object> model = new HashMap<>();
        model.put("name", cart.getProductName());
        model.put("location", "Banglore, India");
        model.put("productPrice", cart.getProductPrice());
        model.put("productName", cart.getProductDescription());
        model.put("productBrand", cart.getProductBrand());
        model.put("productDiscount", cart.getProductDiscount());
        model.put("productManufacturedYear", cart.getProductManufacturedYear());
        model.put("DamageLevel", cart.getProductDamageLevel());
        model.put("productSpecs", cart.getProductSpecs());


        responseEntity= new ResponseEntity<>( cartServiceImpl.createCart(cart), HttpStatus.CREATED);
        return responseEntity;
    }
    @GetMapping("/getCart/{productId}")
    public ResponseEntity<?> getCartById(@PathVariable String productId) {
        responseEntity= new ResponseEntity<>(cartServiceImpl.getCartById(productId), HttpStatus.CREATED);

        return responseEntity;
    }


    @DeleteMapping("/deleteCart/{productId}")
    public ResponseEntity<?> deleteCartById(@PathVariable String productId) {
        responseEntity=new ResponseEntity<Boolean>(cartServiceImpl.deleteCartById(productId), HttpStatus.OK);
        return responseEntity;
    }

}
