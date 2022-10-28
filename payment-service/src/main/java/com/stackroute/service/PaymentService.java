package com.stackroute.service;

import com.stackroute.dto.Payment;
import com.stackroute.dto.ResponseDto;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;

public interface PaymentService {

    ResponseEntity<ResponseDto> createPaymentIntent(Payment payment) throws StripeException;
}
