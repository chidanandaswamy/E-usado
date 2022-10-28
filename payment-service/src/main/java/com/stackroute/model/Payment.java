package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    private UUID id;
    private String orderId;
    private Double amount;
    private String receipt;
    private String status;
    private String razorpaySignature;
    private String paymentId;
    private String paymentRecipientEmail;
}
