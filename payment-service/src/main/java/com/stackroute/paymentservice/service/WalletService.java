package com.stackroute.paymentservice.service;

import org.springframework.http.ResponseEntity;

public interface WalletService {

    ResponseEntity<?> getTotalWalletAmount(String email);
}
