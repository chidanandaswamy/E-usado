package com.stackroute.slotservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value ="database_sequences" )
public class DbSequence {
    @Id
    private String id;
    private int seq;
}