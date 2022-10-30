package com.stackroute.emailservice.Controller;


import com.stackroute.emailservice.dto.MailRequest;
import com.stackroute.emailservice.dto.MailResponse;
import com.stackroute.emailservice.model.OrderService;
import com.stackroute.emailservice.model.SlotBooking;
import com.stackroute.emailservice.model.UserService;
import com.stackroute.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("api/v1")
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping("/OrderConformation")
    public MailResponse sendEmail(@RequestBody OrderService request2) {
        Map<String, Object> model = new HashMap<>();
        model.put("orderID", request2.getId());
        model.put("location", "Banglore, India");
        model.put("orderedProducts", request2.getProducts());
        model.put("orderBuyerEmailId", request2.getBuyerEmail());
        model.put("orderDate", request2.getOrderDate());
        model.put("OrderPaymentStatus", request2.getPaymentStatus());
        model.put("orderTotalAmount", request2.getTotalAmount());
        model.put("OrderStatus", request2.getOrderStatus());

       return service.OrderConformationsendEmail(request2, model);
    }

    @PostMapping("/ThankYouForRegister")
    public MailResponse sendEmailThank(@RequestBody UserService request2) {

        Map<String, Object> model = new HashMap<>();
        model.put("UserEmail", request2.getEmail());
        model.put("UserName", request2.getName());

       return service.sendEmailThankyou(request2, model);
    }

    @PostMapping("/slotBookConfirm")
    public MailResponse sendEmailSlot(@RequestBody SlotBooking request1) {
        Map<String, Object> model = new HashMap<>();
        model.put("slotId", request1.getSlotId());
        model.put("buyerName", request1.getBuyerName());
        model.put("BuyerEmailId", request1.getBuyerEmailId());
        model.put("slotDate", request1.getDate());
        model.put("SlotTime", request1.getDateTimeSlots());
        model.put("sellerName", request1.getSellerName());
        model.put("sellerEmail", request1.getSellerEmailId());
        model.put("SlotDescription", request1.getDescription());
        return service.sendEmailSlotBooking(request1, model);
    }

    @PostMapping("/ProductAdded")
    public MailResponse ProductAddedConforming(@RequestBody MailRequest request1) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request1.getProductName());
        model.put("location", "Banglore, India");
        model.put("productPrice", request1.getProductPrice());
        model.put("productName", request1.getProductDescription());
        model.put("productBrand", request1.getProductBrand());
        model.put("productDiscount", request1.getProductDiscount());
        model.put("productManufacturedYear", request1.getProductManufacturedYear());
        model.put("DamageLevel", request1.getProductDamageLevel());
        model.put("productSpecs", request1.getProductSpecs());


        return service.sendEmailProductAdded(request1, model);
    }


}
