package com.stackroute.userservice.service;
import com.stackroute.userservice.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service
public interface UserService {
     void addUser(User user);
     HashSet<User> findAllUsers();
     User findByEmail(String email) ;

    User findByID(long id) ;

    ResponseEntity<String> updateProductById(long id, User user);

    ResponseEntity<String> deleteById(long id);


}
