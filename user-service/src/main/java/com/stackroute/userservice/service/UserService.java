package com.stackroute.userservice.service;

import com.stackroute.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public interface UserService {
    public void addUser(User user);
    HashSet<User> findAllUsers();
    public User findUserByEmail(String email);
    public void deleteUserByEmail(String email);
    public void deleteUser();
}
