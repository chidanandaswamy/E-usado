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
    public void createCart(@RequestBody Cart cart) {
        cart.setCartId(cartServiceImpl.getSequenceNumber(Cart.SEQUENCE_NAME));

//        Map<String, Object> model = new HashMap<>();
//        model.put("name", cart.getProduct());
//        model.put("location", "Banglore, India");
//        model.put("productPrice", cart.g());
//        model.put("productName", request1.getProductDescription());
//        model.put("productBrand", request1.getProductBrand());
//        model.put("productDiscount", request1.getProductDiscount());
//        model.put("productManufacturedYear", request1.getProductManufacturedYear());
//        model.put("DamageLevel", request1.getProductDamageLevel());
//        model.put("productSpecs", request1.getProductSpecs());
//
//
//        return service.sendEmailProductAdded(request1, model);
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
