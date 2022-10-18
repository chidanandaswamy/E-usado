package com.stackroute.userservice.controller;
import static org.mockito.ArgumentMatchers.anyString;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.Address;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    private User user;


//    @Test
//    void testDeleteSuccess() throws Exception {
//        User user = new User();
//        Address address=new Address();
//
//        user.setName("Ashu");
//        user.setEmail("ashu@gmail.com");
//        user.setPassword("password123");
//        user.setContactNo(9876543210L);
//        user.setGender("male");
//
//        when(userService.deleteUserByEmail(anyString())).thenReturn(user);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/user/{userId}", 123);
//        MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string(
//                        "{\"gender\":\"male\",\"name\":\"Ashu\",\"email\":\"ashu@gmail.com\",\"password"
//                                + "\":\"password123\",\"contactNo\":9876543210}"));
//    }

//    @Test
//    void testDeleteFailure() throws Exception {
//        when(userService.deleteUserByEmail(anyString())).thenThrow(new UserNotFoundException("?"));
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/user/{userId}", 123);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
//                .andExpect(MockMvcResultMatchers.content().string("NO USER PRESENT WITH THIS ID"));
//    }


    @Test
    void testGetByEmailSuccess() throws Exception {
        User user = new User();
        user.setName("Ashu");
        user.setEmail("ashu@gmail.com");
        user.setPassword("password123");
        user.setContactNo(9876543210L);
        user.setGender("male");
        when(userService.findByEmail(user.getEmail())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{email}", "ashu@gmail");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(
                        "{\"gender\":\"male\",\"name\":\"Ashu\",\"email\":\"ashu@gmail.com\",\"password"
                                + "\":\"password123\",\"contactNo\":9876543210}"));
    }

    @Test
    void testGetByEmailException() throws Exception {
        when(userService.findByEmail(anyString())).thenThrow(new UserNotFoundException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{email}", "ashu@gmail");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User Not Found"));
    }



//    @Test
//    void testUpdate() throws Exception {
//        User user = new User();
//        user.setName("Ashu");
//        user.setEmail("ashu@gmail.com");
//        user.setPassword("password123");
//        user.setContactNo(9876543210L);
//        user.setGender("male");
//        when(userService.UpdateByEmail((User) anyString())).thenReturn(user);
//
//        User user1 = new User();
//        user1.setName("Ashu");
//        user1.setEmail("ashu@gmail.com");
//        user1.setPassword("password123");
//        user1.setContactNo(9876543210L);
//        user1.setGender("male");
//        String content = (new ObjectMapper()).writeValueAsString(user1);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/user")
//                .contentType(MediaType.APPLICATION_JSON).content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string(
//                        "{\"gender\":\"male\",\"name\":\"Ashu\",\"email\":\"ashu@gmail.com\",\"password"
//                                + "\":\"password123\",\"contactNo\":9876543210}"));
//    }

//    @Test
//    void testUpdateFailure() throws Exception {
//        when(userService.UpdateByEmail((User) anyString())).thenThrow(new UserNotFoundException("?"));
//
//        User user = new User();
//        user.setName("Ashu");
//        user.setEmail("ashu@gmail.com");
//        user.setPassword("password123");
//        user.setContactNo(9876543210L);
//        user.setGender("male");
//        String content = (new ObjectMapper()).writeValueAsString(user);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/user")
//                .contentType(MediaType.APPLICATION_JSON).content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
//                .andExpect(MockMvcResultMatchers.content().string("User Not Found"));
//    }






//    @Test
//    void testCreateSuccess() throws Exception {
//        User user = new User();
////       Address address=new Address();
//        user.setName("Ashu");
//        user.setEmail("ashu@gmail.com");
//        user.setPassword("password123");
//        user.setContactNo(9876543210L);
//        user.setGender("male");

//   when(userService.addUser((User) any())).thenReturn(user);
//        when(userService.addUser((User) any())).thenReturn(user);
//
//        User user1 = new User();
//        user1.setName("Ashu");
//        user1.setEmail("ashu@gmail.com");
//        user1.setPassword("password123");
//        user1.setContactNo(9876543210L);
//        user1.setGender("male");
//
//
//        String content = (new ObjectMapper()).writeValueAsString(user1);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/user")
//                .contentType(MediaType.APPLICATION_JSON).content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string(
//                        "{\"gender\":\"male\",\"name\":\"Ashu\",\"email\":\"ashu@gmail.com\",\"password"
//                                + "\":\"password123\",\"contactNo\":9876543210}"));
//    }
//
//
//    @Test
//    void testCreateFailure() throws Exception {
//        when(userService.addUser((User) any())).thenThrow(new UserNotFoundException("?"));
//
//        User user = new User();
//        user.setCity("Delhi");
//        user.setContact(9861690000L);
//        user.setEmail("stallin@gmail.com");
//        user.setGender(Gender.MALE);
//        user.setName("Stallin");
//        user.setPassword("password123");
//        user.setRole(Role.BOOKIE);
//        user.setUserId(123);
//        String content = (new ObjectMapper()).writeValueAsString(user);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/user")
//                .contentType(MediaType.APPLICATION_JSON).content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409))
//                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
//                .andExpect(MockMvcResultMatchers.content().string("User Already Exists"));
//    }



}
