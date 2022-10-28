package com.stackroute.controller;

import com.stackroute.dto.CheckOutDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UIController {
    @Value("${stripe.publishable-key}")
    private String stripePublicKey;

    @GetMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home(Model model){
        System.out.println("reached here ");
        model.addAttribute("checkoutForm",new CheckOutDto());
        return "index";
    }

    @PostMapping("/")
    public String checkout(@ModelAttribute CheckOutDto checkoutForm,
                           BindingResult bindingResult,
                           Model model){
        System.out.println(checkoutForm);
        if (bindingResult.hasErrors()){
            return "index";
        }

        model.addAttribute("stripePublicKey",stripePublicKey);
        model.addAttribute("amount",checkoutForm.getAmount()*100);
        model.addAttribute("email", checkoutForm.getEmail());
        model.addAttribute("featureRequest", checkoutForm.getFeatureRequest());
        return "checkout";
    }
}
