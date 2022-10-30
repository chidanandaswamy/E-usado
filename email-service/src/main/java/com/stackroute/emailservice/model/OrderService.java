package com.stackroute.emailservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderService {
    private long id;
    private String buyerEmail;
    private String orderDate;
    private String products;
    private Double totalAmount;
    private String paymentStatus;
    private String orderStatus;
}
