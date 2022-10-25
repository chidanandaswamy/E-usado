package com.stackroute.orderservice.service;

import com.stackroute.orderservice.model.Cart;
import com.stackroute.orderservice.model.Product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerService {

    @Autowired
   public CartService cartService;

//    @RabbitListener(queues = "productServiceQueue")
//    public void receiveMessage(HashMap<String, Object> message) {
//        System.out.println("Received <" + message + ">");
//    }


    @RabbitListener(queues = "productServiceQueue")
    public void ProductAddedConforming(Product request1) {

        Map<String, Object> model = new HashMap<>();
        model.put("name", "E-usado");
        model.put("productName", request1.getProductName());
        model.put("location", "Banglore, India");
        model.put("productPrice", request1.getProductPrice());
        model.put("productDescription", request1.getProductDescription());
        model.put("productBrand", request1.getProductBrand());
        model.put("productDiscount", request1.getProductDiscount());
        model.put("productManufacturedYear", request1.getProductManufacturedYear());
        model.put("DamageLevel", request1.getProductDamageLevel());
        model.put("productSpecs", request1.getProductOwnerEmail());

//    System.out.println(request1);

        cartService.createCart(new Cart());
    }
}
