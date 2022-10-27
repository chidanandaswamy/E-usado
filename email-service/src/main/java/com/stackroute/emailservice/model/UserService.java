package com.stackroute.emailservice.model;

//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@ToString
public class UserService {
    private String Useremail;
    private String Userpassword;
    private String Username;

    public UserService(String useremail, String userpassword, String username) {
       super();
        Useremail = useremail;
        Userpassword = userpassword;
        Username = username;
    }

    public UserService() {
   super();
    }

    public String getUseremail() {
        return Useremail;
    }

    public void setUseremail(String useremail) {
        Useremail = useremail;
    }

    public String getUserpassword() {
        return Userpassword;
    }

    public void setUserpassword(String userpassword) {
        Userpassword = userpassword;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "Useremail='" + Useremail + '\'' +
                ", Userpassword='" + Userpassword + '\'' +
                ", Username='" + Username + '\'' +
                '}';
    }
}
