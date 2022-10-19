package com.stackroute.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.UUID;
@Data
public class MailRequest {
    private String name;
    private String to;
    private String from;
    private String subject;

    private String productId;

    private String productName;

    private String userName;

    private String OrderId;

    private String SlotTime;
    private String UserEmail;

    public MailRequest() {
     super();
    }

    public MailRequest(String name, String to, String from, String subject, String productId, String productName, String userName, String orderId, String slotTime, String userEmail) {
       super();
        this.name = name;
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.productId = productId;
        this.productName = productName;
        this.userName = userName;
        OrderId = orderId;
        SlotTime = slotTime;
        UserEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getSlotTime() {
        return SlotTime;
    }

    public void setSlotTime(String slotTime) {
        SlotTime = slotTime;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    private UUID id;
//    private String productName;
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
//    private Binary[] productImages;
}
