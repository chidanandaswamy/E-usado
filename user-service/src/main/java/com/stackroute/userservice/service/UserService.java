package com.stackroute.userservice.service;
import com.stackroute.userservice.model.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {
//    void addUser(User user) ;

    User addUser(User user);

//    HashSet<User> findAllUsers();
    List<User> findAllUsers();
    User findByEmail(String email) ;

    boolean deleteUserByEmail(String email);

    User UpdateByEmail(User user,String email);



}
