package com.stackroute.service;

import com.stackroute.dto.MailRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RabbitMQConsumerService {

    @Autowired
    private EmailService service;


    //product added conformation mail
    @RabbitListener(queues = "productServiceQueue")
    public void ProductAddedConforming(MailRequest request1) {

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
       model.put("productSpecs", request1.getProductSpecs());



         service.sendEmailProductAdded(request1, model);
    }
            //for registration conformation mail
//    @RabbitListener(queues = "User_queue")
//    public void sendEmailThank(MailRequest request2) {
//
//        Map<String, Object> model = new HashMap<>();
//        model.put("Username", request2.getUserName());
//        model.put("UserEmail", request2.getUserEmail());
//
//
//
//         service.sendEmailThankyou(request2, model);
//    }


//
//
//    //slot booked conformation mail
//    @RabbitListener(queues = "User-booking_queue")
//    public void sendEmailSlot(MailRequest request1) {
//        Map<String, Object> model = new HashMap<>();
//        model.put("name", request1.getProductName());
//        model.put("location", "Banglore, India");
//        model.put("productPrice", request1.getProductPrice());
//        model.put("productName", request1.getProductDescription());
//        model.put("userEmail", request1.getProductBrand());
//        service.sendEmailSlotBooking(request1, model);
//    }
//
//    @RabbitListener(queues = "Slot-booking_queue")
//    public void OrderConformation(MailRequest request1) {
//        Map<String, Object> model = new HashMap<>();
//        model.put("name", request1.getProductName());
//
//    }
}
