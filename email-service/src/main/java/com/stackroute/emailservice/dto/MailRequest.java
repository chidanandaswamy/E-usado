package com.stackroute.emailservice.dto;

import java.util.HashMap;
import java.util.UUID;

public class MailRequest {


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




    public MailRequest() {
        super();
    }

    public MailRequest(UUID id, String productName, Double productPrice, String productBrand, String productCategory, String productDescription, HashMap<String, String> productSpecs, String productManufacturedYear, Long productPurchasedDate, String productOwnerEmail, Float productDiscount, Boolean warrantyStatus, Float productDamageLevel) {
        super();
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productBrand = productBrand;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
        this.productSpecs = productSpecs;
        this.productManufacturedYear = productManufacturedYear;
        this.productPurchasedDate = productPurchasedDate;
        this.productOwnerEmail = productOwnerEmail;
        this.productDiscount = productDiscount;
        this.warrantyStatus = warrantyStatus;
        this.productDamageLevel = productDamageLevel;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public HashMap<String, String> getProductSpecs() {
        return productSpecs;
    }

    public void setProductSpecs(HashMap<String, String> productSpecs) {
        this.productSpecs = productSpecs;
    }

    public String getProductManufacturedYear() {
        return productManufacturedYear;
    }

    public void setProductManufacturedYear(String productManufacturedYear) {
        this.productManufacturedYear = productManufacturedYear;
    }

    public Long getProductPurchasedDate() {
        return productPurchasedDate;
    }

    public void setProductPurchasedDate(Long productPurchasedDate) {
        this.productPurchasedDate = productPurchasedDate;
    }

    public String getProductOwnerEmail() {
        return productOwnerEmail;
    }

    public void setProductOwnerEmail(String productOwnerEmail) {
        this.productOwnerEmail = productOwnerEmail;
    }

    public Float getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(Float productDiscount) {
        this.productDiscount = productDiscount;
    }

    public Boolean getWarrantyStatus() {
        return warrantyStatus;
    }

    public void setWarrantyStatus(Boolean warrantyStatus) {
        this.warrantyStatus = warrantyStatus;
    }

    public Float getProductDamageLevel() {
        return productDamageLevel;
    }

    public void setProductDamageLevel(Float productDamageLevel) {
        this.productDamageLevel = productDamageLevel;
    }

    @Override
    public String toString() {
        return "MailRequest{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productBrand='" + productBrand + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productSpecs=" + productSpecs +
                ", productManufacturedYear='" + productManufacturedYear + '\'' +
                ", productPurchasedDate=" + productPurchasedDate +
                ", productOwnerEmail='" + productOwnerEmail + '\'' +
                ", productDiscount=" + productDiscount +
                ", warrantyStatus=" + warrantyStatus +
                ", productDamageLevel=" + productDamageLevel +
                '}';
    }

}
