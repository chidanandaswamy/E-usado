package com.stackroute.paymentservice.service;

import com.stackroute.paymentservice.dto.CheckOutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class UIServiceImpl implements UIService{

    @Value("${stripe.publishable-key}")
    private String stripePublicKey;

    @Autowired
    private PaymentServiceImpl paymentServiceIMpl;

    @Override
    public String checkout(CheckOutDto checkOutDto, BindingResult bindingResult, Model model) {
        System.out.println("payment" + checkOutDto);
        if (bindingResult.hasErrors()){
            return "index";
        }

        model.addAttribute("stripePublicKey",stripePublicKey);
        model.addAttribute("amount",checkOutDto.getAmount());
        model.addAttribute("paymentDescription", checkOutDto.getPaymentDescription());
        model.addAttribute("senderEmail", checkOutDto.getSenderEmail());
        model.addAttribute("senderName", checkOutDto.getSenderName());
        model.addAttribute("receiverEmail", checkOutDto.getReceiverEmail());
        model.addAttribute("receiverName", checkOutDto.getReceiverName());

        model.addAttribute("country", checkOutDto.getCountry());
        model.addAttribute("state", checkOutDto.getState());
        model.addAttribute("city", checkOutDto.getCity());
        model.addAttribute("zipCode", checkOutDto.getZipCode());
        model.addAttribute("line1", checkOutDto.getLine1());

        System.out.println(model.getAttribute("senderEmail"));

        return "checkout";
    }
}
