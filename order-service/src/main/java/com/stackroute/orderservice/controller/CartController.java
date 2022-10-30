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
        model.put("productName", cart.getProductName());
        model.put("productPrice", cart.getProductPrice());
        model.put("productBrand", cart.getProductBrand());
        model.put("productDescription", cart.getProductDescription());
        model.put("productManufacturedYear", cart.getProductManufacturedYear());
        model.put("productPurchasedDate", cart.getProductPurchasedDate());
//        model.put("productOwnerEmail", cart.getProductOwnerEmail());
        model.put("productDiscount", cart.getProductDiscount());
        model.put("warrantyStatus", cart.getWarrantyStatus());
        model.put("damageLevel", cart.getProductDamageLevel());
        model.put("location", cart.getLocation());
        model.put("productAvailability", cart.getProductAvailability());
        model.put("productAddedTime", cart.getProductAddedTime());
        model.put("cartOwnerEmail", cart.getCartOwnerEmail());


        responseEntity= new ResponseEntity<>( cartServiceImpl.createCart(cart), HttpStatus.CREATED);
        return responseEntity;
    }
    @GetMapping("/getCart/{cartOwnerEmail}")
    public ResponseEntity<?> getCartById(@PathVariable String cartOwnerEmail) {
        responseEntity= new ResponseEntity<>(cartServiceImpl.getCartById(cartOwnerEmail), HttpStatus.CREATED);

        return responseEntity;
    }


    @DeleteMapping("/deleteCart/{cartOwnerEmail}")
    public ResponseEntity<?> deleteCartById(@PathVariable String cartOwnerEmail) {
        responseEntity=new ResponseEntity<Boolean>(cartServiceImpl.deleteCartById(cartOwnerEmail), HttpStatus.OK);
        return responseEntity;
    }

}
