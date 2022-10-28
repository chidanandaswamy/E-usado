package com.stackroute.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {

    private UUID addressID;

    @NotNull(message = "Enter your house number")
    private long houseNumber;

    @NotNull(message = "Enter your building name")
    private String buildingName;
    private String streetName;
    private String landmark;
    private long pinCode;
    private String city;
    private String state;



}