package com.stackroute.userservice.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Address {

    private UUID addressID;
    private long houseNumber;
    private String buildingName;
    private String streetName;
    private String landmark;
    private long pinCode;
    private String city;
    private String state;


}
