package com.stackroute.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    @Override
    public ResponseEntity<?> getTotalWalletAmount(String email) {
        return new ResponseEntity<Long>(paymentServiceImpl.getWalletAmount(email), HttpStatus.OK) ;
    }

}
