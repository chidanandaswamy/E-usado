package com.stackroute.userservice.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

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