package com.stackroute.paymentservice.exception;

public class CustomerCreationFailedException extends Exception{

    public CustomerCreationFailedException() {
        super();
    }

    public CustomerCreationFailedException(String message) {
        super(message);
    }
}
