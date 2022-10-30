package com.stackroute.paymentservice.controller;

import com.stackroute.paymentservice.service.WalletServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    @Autowired
    private WalletServiceImpl walletServiceImpl;

    @GetMapping("/wallet-amount/{email}")
    public ResponseEntity<?> getTotalWalletAmount(@PathVariable String email){
        return walletServiceImpl.getTotalWalletAmount(email);
    }
}
