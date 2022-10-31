package com.stackroute.paymentservice.service;

import com.stackroute.paymentservice.dto.CheckOutDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface UIService {

    String checkout(CheckOutDto checkOutDto, BindingResult bindingResult, Model model);
}
