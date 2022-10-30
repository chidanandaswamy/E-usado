package com.stackroute.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {
    private String id;
    private String buyerEmail;
    private String orderDate;
    private String productName;
    private Double productPrice;
    private String productBrand;
    private Double totalAmount;
    private String paymentStatus;
    private String orderStatus;
}
