package com.stackroute.controller;

import com.razorpay.RazorpayException;
import com.stackroute.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Double amount) {
        return paymentServiceImpl.createOrder(amount);
    }

}
