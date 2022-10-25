package com.stackroute.userservice.model;

//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@ToString
public class UserDTO {
    private String UserEmail;
    private String UserPassword;
    private String UserName;

    public UserDTO(String UserEmail, String UserPassword, String UserName) {
       super();
        this.UserEmail = UserEmail;
        this.UserPassword = UserPassword;
        this.UserName = UserName;
    }

    public UserDTO() {
        super();
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "UserEmail='" + UserEmail + '\'' +
                ", UserPassword='" + UserPassword + '\'' +
                ", UserName='" + UserName + '\'' +
                '}';
    }
}
