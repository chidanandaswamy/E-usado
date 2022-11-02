package com.stackroute.authenticationservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.authenticationservice.domain.AuthRequest;
import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.repository.UserRepository;
import com.stackroute.authenticationservice.security.SecurityTokenGenerator;
import com.stackroute.authenticationservice.service.UserService;
import com.stackroute.authenticationservice.service.UserServiceImpl;

import java.util.HashMap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;


    @Test
    void testForgotPassword() throws UserAlreadyExistsException, Exception {
        User user = new User();
        user.setEmail("azarnew1@gmail");
        user.setPassword("newpassword");

        User user1 = new User();
        user1.setEmail("azarnew1@gmail");
        user1.setPassword("newpassword");
        when(userService.saveUser((User) any())).thenReturn(user1);
        when(userService.findByEmail((String) any())).thenReturn(user);

        AuthRequest authRequest = new AuthRequest();
        authRequest.setChangePassword("azhar123");
        authRequest.setEmailId("azarnew1@gmail");
        String content = (new ObjectMapper()).writeValueAsString(authRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/forgetPassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(
                        MockMvcResultMatchers.content().string("azarnew1@gmail password has been Changed Successfully"));
    }
    @Test
    void testLoginUser() throws Exception {
        User user = new User();
        user.setEmail("Arjun.mk@gmail");
        user.setPassword("Arjunmk");
        when(userService.findByEmailAndPassword((String) any(), (String) any())).thenReturn(user);
        when(securityTokenGenerator.generateToken((User) any())).thenReturn(new HashMap<>());

        User user1 = new User();
        user1.setEmail("Arjun.mk@gmail");
        user1.setPassword("Arjunmk");
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }
}

