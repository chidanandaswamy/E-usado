package com.stackroute.paymentservice.controller;

import com.stackroute.paymentservice.dto.CheckOutDto;
import com.stackroute.paymentservice.service.UIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UIController {

    @Autowired
    private UIServiceImpl uiServiceImpl;

    @GetMapping(path = "/visual", produces = MediaType.TEXT_HTML_VALUE)
    public String home(Model model){
        System.out.println("reached here ");
        model.addAttribute("checkoutForm",new CheckOutDto());
        return "index";
    }

    @PostMapping("/checkout")
    public String checkout(@ModelAttribute CheckOutDto checkOutDto,
                           BindingResult bindingResult,
                           Model model){

        return uiServiceImpl.checkout(checkOutDto, bindingResult, model);
    }
}
