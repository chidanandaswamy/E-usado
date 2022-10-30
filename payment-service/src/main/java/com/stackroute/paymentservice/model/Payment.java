package com.stackroute.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "payment")
public class Payment {

    @Id
    private String paymentId;
    private String paymentDescription;
    private String paymentStatus;

    private String productId;
    private Long amount;

    private String senderEmail;
    private String senderName;
    private Address billingAddress;

    private String receiverEmail;
    private String receiverName;

    private String stripeCustomerId;
    private String stripePaymentId;

}
