package com.stackroute.slotservice.exception;

import java.util.NoSuchElementException;

public class SlotNotFoundException extends NoSuchElementException {

    public SlotNotFoundException(){

    }
    public SlotNotFoundException(String message){
        super(message);
    }
}
