package com.stackroute.cartservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Cart")
public class Cart {

    @Transient
    public static final String SEQUENCE_NAME ="user_sequence";

    @Id
    private long id;
    private String userId;
    private String productId;
}
