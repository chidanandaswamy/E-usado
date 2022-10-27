package com.stackroute.authenticationservice.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.stackroute.authenticationservice.domain.AuthRequest;
import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.InvalidCredentialsException;
import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.security.JWTSecurityTokenGeneratorImpl;
import com.stackroute.authenticationservice.service.UserServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class UserControllerTest {
    /**
     * Method under test: {@link UserController#loginUser(User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoginUser() throws InvalidCredentialsException {

        UserServiceImpl userService = new UserServiceImpl(null);
        UserController userController = new UserController(userService, new JWTSecurityTokenGeneratorImpl());

        User user = new User();
        user.setEmail("Arjun.mk@gmail");
        user.setPassword("Arjunmk");
        userController.loginUser(user);
    }

    /**
     * Method under test: {@link UserController#forgotPassword(AuthRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testForgotPassword() throws UserAlreadyExistsException, Exception {

        UserServiceImpl userService = new UserServiceImpl(null);
        UserController userController = new UserController(userService, new JWTSecurityTokenGeneratorImpl());

        AuthRequest authRequest = new AuthRequest();
        authRequest.setChangePassword("abc");
        authRequest.setEmailId("azharuddin.n@gmail");
        userController.forgotPassword(authRequest);
    }

    /**
     * Method under test: {@link UserController#forgotPassword(AuthRequest)}
     */
    @Test
    void testForgotPassword2() throws UserAlreadyExistsException, Exception {
        UserServiceImpl userService = new UserServiceImpl(null);
        assertThrows(Exception.class,
                () -> (new UserController(userService, new JWTSecurityTokenGeneratorImpl())).forgotPassword(null));
    }
}

