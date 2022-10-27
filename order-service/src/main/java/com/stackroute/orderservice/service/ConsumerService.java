package com.stackroute.orderservice.service;

import com.stackroute.orderservice.dto.ProductDto;
import com.stackroute.orderservice.model.Cart;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
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
        model.put("location", "Banglore, India");
        model.put("productPrice", cart.getProductPrice());
        model.put("productDescription", cart.getProductDescription());
        model.put("productBrand", cart.getProductBrand());
        model.put("productDiscount", cart.getProductDiscount());
        model.put("productManufacturedYear", cart.getProductManufacturedYear());
        model.put("DamageLevel", cart.getProductDamageLevel());
        model.put("productSpecs", cart.getProductOwnerEmail());


        cartServiceImpl.createCart(cart);
    }
}
