package com.stackroute.userservice.model;
import lombok.*;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Document(value="user_table")
public class User {

    @Id
    private String email;
    private String password;


    private long contactNo;
    @NotNull(message = "Enter your full name for registration")
    private String name;

    private String gender;

    private Address address;

}