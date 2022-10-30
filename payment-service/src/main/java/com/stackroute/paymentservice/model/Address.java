package com.stackroute.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    private String line1;
    private String line2;
    private String country;
    private String state;
    private String city;
    private String zipCode;

}
