package com.stackroute.userservice.model;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(value="usado_user")
public class User {
    @Id
    private String email;
    private String name;
    private long contactNo;
    private String gender;

    private Address address;

}
