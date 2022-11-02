package com.stackroute.paymentservice.controller;

import com.stackroute.paymentservice.model.Payment;
import com.stackroute.paymentservice.service.PaymentServiceImpl;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    @PostMapping("/create-payment-intent")
    public ResponseEntity<?> createPaymentIntent(@RequestBody Payment payment) throws StripeException {
        return paymentServiceImpl.createPaymentIntent(payment);
    }

    @GetMapping("/payments/sender-payments/{senderEmail}")
    public ResponseEntity<?> getPaymentsBySenderEmail(@PathVariable String senderEmail){
        return paymentServiceImpl.getPaymentsBySenderEmail(senderEmail);
    }

    @GetMapping("/payments/receiver-payments/{receiverEmail}")
    public ResponseEntity<?> getPaymentsByReceiverEmail(@PathVariable String receiverEmail){
        return paymentServiceImpl.getPaymentsByReceiverEmail(receiverEmail);
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable String id){
        return paymentServiceImpl.getPaymentById(id);
    }

    @PutMapping("/payment/update-status/{paymentId}")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable String paymentId,@RequestBody String status) {
        return paymentServiceImpl.updatePaymentStatus(paymentId, status);
    }

}
