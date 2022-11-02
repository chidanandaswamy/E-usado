package com.stackroute.emailservice.service;


import com.stackroute.emailservice.dto.MailRequest;
import com.stackroute.emailservice.model.OrderService;
import com.stackroute.emailservice.model.SlotBooking;
import com.stackroute.emailservice.model.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

;

@Component
public class RabbitMQConsumerService {

    @Autowired
    private EmailService service;


    //product added conformation mail
    @RabbitListener(queues = "productMailQueue")
    public void ProductAddedConforming(MailRequest request1) {

        System.out.println("porduct mail" + request1);

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

    @RabbitListener(queues = "Slot_queue")
    public void sendEmailSlot(SlotBooking request1) {

        System.out.println("slot service" + request1);

        Map<String, Object> model = new HashMap<>();
        model.put("slotId", request1.getSlotId());
        model.put("buyerName", request1.getBuyerName());
        model.put("BuyerEmailId", request1.getBuyerEmailId());
        model.put("slotDate", request1.getDate());
        model.put("SlotTime", request1.getDateTimeSlots());
        model.put("sellerName", request1.getSellerName());
        model.put("sellerEmail", request1.getSellerEmailId());
        model.put("SlotDescription", request1.getDescription());
        System.out.println(request1);
        service.sendEmailSlotBooking(request1, model);
    }


//            for registration conformation mail
    @RabbitListener(queues = "User_queue_email")
    public void sendEmailThank(UserService request2) {

        System.out.println("useer" + request2);

        Map<String, Object> model = new HashMap<>();
        model.put("UserEmail", request2.getEmail());
        model.put("UserName", request2.getName());

         service.sendEmailThankyou(request2, model);
    }


    //slot booked conformation mail
    @RabbitListener(queues = "Order_queue")
    public void OrderConformation(OrderService request2) {

        System.out.println("order " + request2);

        Map<String, Object> model = new HashMap<>();
        model.put("orderID", request2.getId());
        model.put("location", "Banglore, India");
        model.put("orderedProducts", request2.getProducts());
        model.put("orderBuyerEmailId", request2.getBuyerEmail());
        model.put("orderDate", request2.getOrderDate());
        model.put("OrderPaymentStatus", request2.getPaymentStatus());
        model.put("orderTotalAmount", request2.getTotalAmount());
        model.put("OrderStatus", request2.getOrderStatus());

        System.out.println(request2);
        service.OrderConformationsendEmail(request2, model);
    }

}
