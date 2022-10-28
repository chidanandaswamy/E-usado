package com.stackroute.productservice.dto;

import com.stackroute.productservice.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    private String id;
    private String productName;
    private Double productPrice;
    private String productBrand;
    private String productCategory;
    private String productDescription;
    private HashMap<String, String> productSpecs;
    private String productManufacturedYear;
    private Long productPurchasedDate;
    private String productOwnerEmail;
    private Float productDiscount;
    private Boolean warrantyStatus;
    private Float productDamageLevel;
    private Location location;
    private Boolean productAvailability;
    private Long productAddedTime;
}

