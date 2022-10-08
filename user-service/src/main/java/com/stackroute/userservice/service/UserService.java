package com.stackroute.userservice.service;
import com.stackroute.userservice.model.User;
import org.springframework.stereotype.Service;
import java.util.HashSet;

@Service
public interface UserService {
     void addUser(User user);
    HashSet<User> findAllUsers();
     User findByEmail(String email) ;

    User deleteUserByEmail(String email);

    User updateUser(User user);

    User UpdateByEmail(User user,String email);

}
