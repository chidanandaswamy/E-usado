package com.stackroute.paymentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PaymentExceptionController {

    @ExceptionHandler(value = PaymentNotFound.class)
    public ResponseEntity<Object> exception(PaymentNotFound exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PaymentFailedException.class)
    public ResponseEntity<Object> exception(PaymentFailedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CustomerCreationFailedException.class)
    public ResponseEntity<Object> exception(CustomerCreationFailedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
