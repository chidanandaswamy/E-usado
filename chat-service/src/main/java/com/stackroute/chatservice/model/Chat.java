package com.stackroute.chatservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.xml.stream.Location;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="chat")
public class Chat {

   @Transient
    public static final String SEQUENCE_NAME = "booking_sequence";
    @Id
    private long questionId;
    private String productId;
    private String question;
    private List reply;
    private String userEmail;


}
