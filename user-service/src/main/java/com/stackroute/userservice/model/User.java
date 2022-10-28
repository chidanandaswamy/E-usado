package com.stackroute.userservice.model;
import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Document(value="eusado_user")
public class User {

    @NotNull(message = "Enter your email")
    @Id
    private String email;

    @NotNull(message = "Enter your password")
    @Pattern(message="length must be 8",regexp="^[a-zA-Z0-9_!#$%&]{8}")
    private String password;

    @NotNull(message = "Enter your contact number")
    private long contactNo;

    @NotNull(message = "Enter your full name for registration")
    private String name;

    @NotNull(message = "Enter your gender")
    private String gender;

    private Address address;

}