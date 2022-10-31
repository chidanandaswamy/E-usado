package com.stackroute.orderservice.service;


import com.stackroute.orderservice.model.Cart;
//import com.stackroute.orderservice.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ConsumerService {

    @Autowired
   public CartServiceImpl cartServiceImpl;

//    @RabbitListener(queues = "productOrderQueue")
//    public void receiveMessage(HashMap<String, Object> message) {
//        System.out.println("Received <" + message + ">");
//    }


    @RabbitListener(queues = "productOrderQueue")
    public void createCart(Cart cart) {

        Map<String, Object> model = new HashMap<>();
        model.put("name", "E-usado");
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



        cartServiceImpl.createCart(cart);
    }



//    @RabbitListener(queues = "Cart-queue")
//    public void buy(Order order) {
//
//        Map<String, Object> model = new HashMap<>();
//        model.put("name", "E-usado");
//        model.put("productName", cart.getProductName());
//        model.put("location", "Banglore, India");
//        model.put("productPrice", cart.getProductPrice());
//        model.put("productDescription", cart.getProductDescription());
//        model.put("productBrand", cart.getProductBrand());
//        model.put("productDiscount", cart.getProductDiscount());
//        model.put("productManufacturedYear", cart.getProductManufacturedYear());
//        model.put("DamageLevel", cart.getProductDamageLevel());
//        model.put("productSpecs", cart.getProductOwnerEmail());
//
//
//        cartServiceImpl.createCart(cart);
//    }
}
