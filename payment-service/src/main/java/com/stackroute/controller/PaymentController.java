package com.stackroute.controller;

import com.stackroute.dto.Payment;
import com.stackroute.dto.ResponseDto;
import com.stackroute.service.PaymentServiceImpl;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
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

    @PostMapping("/create-payment-intent")
    public ResponseEntity<ResponseDto> createPaymentIntent(@RequestBody Payment payment) throws StripeException {
        System.out.println("reached payment intetny");
        return paymentServiceImpl.createPaymentIntent(payment);
    }

}
