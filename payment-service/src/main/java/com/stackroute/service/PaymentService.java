package com.stackroute.service;

import com.razorpay.RazorpayException;
import com.stackroute.model.Payment;
import org.springframework.http.ResponseEntity;

public interface PaymentService {

    ResponseEntity<?> createOrder(Double amount);
    ResponseEntity<?> updatePayment(Payment payment);
    ResponseEntity<?> getPayments();
}
