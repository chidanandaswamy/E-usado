package com.stackroute.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;





@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "Order")
@ToString
public class Order {

    @Transient
    public static final String SEQUENCE_NAME ="user_sequence";

    @Id
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
