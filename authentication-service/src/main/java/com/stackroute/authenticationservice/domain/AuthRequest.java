package com.stackroute.authenticationservice.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthRequest {
    private String emailId;
    private String ChangePassword;

}
