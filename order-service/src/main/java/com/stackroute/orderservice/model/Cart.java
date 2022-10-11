package com.stackroute.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Cart")
public class Cart {

    @Transient
    public static final String SEQUENCE_NAME ="user_sequence";

    @Id
    private long cartId;
    private String userName;
    private String productName;


}
