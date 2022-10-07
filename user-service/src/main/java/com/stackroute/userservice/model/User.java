package com.stackroute.userservice.model;
import lombok.*;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Document(value="user_table")
public class User {

    @Transient
    public static final String SEQUENCE_NAME = "booking_sequence";

    @Id
    private long userId;

    private String password;

    private String email;

  private long contactNo;
    @NotNull(message = "Enter your full name for registration")
   private String name;

   private String gender;
   private Address address;

}
