package com.stackroute.productservice.exception;

import java.util.NoSuchElementException;

public class ProductNotFoundException extends NoSuchElementException {

    public ProductNotFoundException() {

    }

    public ProductNotFoundException(String s) {
        super(s);
    }
}
