package com.stackroute.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashMap;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Cart")
@ToString
public class Cart {

    @Transient
    public static final String SEQUENCE_NAME ="user_sequence";

    @Id
    private String cartId;
    private String productId;
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
    private String cartOwnerEmail;


}
