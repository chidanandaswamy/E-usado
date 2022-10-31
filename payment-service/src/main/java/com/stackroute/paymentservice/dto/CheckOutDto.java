package com.stackroute.paymentservice.dto;

import com.stackroute.paymentservice.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CheckOutDto {

    private String paymentDescription;
    private String paymentStatus;

    private String productId;
    private Long amount;

    private String senderEmail;
    private String senderName;

    private String receiverEmail;
    private String receiverName;

    private String line1;
    private String country;
    private String state;
    private String city;
    private String zipCode;
}
