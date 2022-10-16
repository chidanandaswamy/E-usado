package rabbitmqdto;


import lombok.Data;

import java.util.UUID;

import org.bson.types.Binary;
import java.util.HashMap;


@Data
public class Product {


    private UUID id;
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
    private Binary[] productImages;

}
