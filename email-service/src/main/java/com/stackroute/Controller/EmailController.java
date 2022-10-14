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
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping("/OrderConformation")
    public MailResponse sendEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request.getName());

        model.put("location", "Banglore, India");

        return service.sendEmail(request, model);
    }

    @PostMapping("/Thankyou")
    public MailResponse sendEmailThank(@RequestBody MailRequest request1) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request1.getName());
        model.put("location", "Banglore, India");

        return service.sendEmailThankyou(request1, model);
    }

    @PostMapping("/slotBookConfirm")
    public MailResponse sendEmailSlot(@RequestBody MailRequest request1) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request1.getName());
        model.put("location", "Banglore, India");

        return service.sendEmailSlotBooking(request1, model);
    }


}
