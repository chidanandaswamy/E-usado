package com.stackroute.Controller;

import com.stackroute.dto.MailRequest;
import com.stackroute.dto.MailResponse;
import com.stackroute.service.EmailService;
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
    public MailResponse sendEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request.getProductName());
        model.put("location", "Banglore, India");
        model.put("productPrice", request.getProductPrice());
        model.put("productName", request.getProductDescription());
        model.put("productBrand", request.getProductBrand());
        model.put("productDiscount", request.getProductDiscount());
        model.put("productManufacturedYear", request.getProductManufacturedYear());
        model.put("DamageLevel", request.getProductDamageLevel());
        model.put("productSpecs", request.getProductSpecs());

        return service.sendEmail(request, model);
    }

    @PostMapping("/ThankYouForRegister")
    public MailResponse sendEmailThank(@RequestBody MailRequest request1) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request1.getProductName());
        model.put("location", "Banglore, India");
        model.put("productPrice", request1.getProductPrice());
        model.put("productName", request1.getProductDescription());
        model.put("userEmail", request1.getProductBrand());
        return service.sendEmailThankyou(request1, model);
    }

    @PostMapping("/slotBookConfirm")
    public MailResponse sendEmailSlot(@RequestBody MailRequest request1) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request1.getProductName());
        model.put("location", "Banglore, India");
        model.put("productPrice", request1.getProductPrice());
        model.put("productName", request1.getProductDescription());
        model.put("userEmail", request1.getProductBrand());
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
