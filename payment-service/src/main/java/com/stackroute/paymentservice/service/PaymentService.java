package com.stackroute.paymentservice.service;

import com.stackroute.paymentservice.exception.CustomerCreationFailedException;
import com.stackroute.paymentservice.exception.PaymentFailedException;
import com.stackroute.paymentservice.model.Payment;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;

public interface PaymentService {

    ResponseEntity<?> createPaymentIntent(Payment payment) throws StripeException, CustomerCreationFailedException, PaymentFailedException;

    ResponseEntity<?> getPaymentsBySenderEmail(String email);
    ResponseEntity<?> getPaymentById(String paymentId);
    ResponseEntity<?> updatePaymentStatus(String paymentId, String status);

    ResponseEntity<?> getPaymentsByReceiverEmail(String email);
}
