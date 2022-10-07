package com.stackroute.orderservice.exception;

import java.util.NoSuchElementException;

public class OrderNotFoundException extends NoSuchElementException {

    public OrderNotFoundException() {

    }

    public OrderNotFoundException(String s) {
        super(s);
    }
}

