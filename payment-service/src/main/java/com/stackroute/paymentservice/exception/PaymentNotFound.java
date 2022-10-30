package com.stackroute.paymentservice.exception;

import javassist.NotFoundException;

public class PaymentNotFound extends NotFoundException {

    public PaymentNotFound(String msg) {
        super(msg);
    }

}
