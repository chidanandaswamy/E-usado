package com.stackroute.userservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.Address;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserServiceImpl;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserController userController;

    @MockBean
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserController#deleteUserByEmail(String)}
     */
    @Test
    void testDeleteUserByEmail() throws Exception {
        when(userServiceImpl.deleteUserByEmail((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/users/delete/{email}",
                "jane.doe@example.org");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link UserController#findAll()}
     */
    @Test
    void testFindAll() throws Exception {
        when(userServiceImpl.findAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#getByEmail(String, HttpSession)}
     */
    @Test
    void testGetByEmail() throws Exception {
        User user = new User();
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");
        when(userServiceImpl.findByEmail((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/{email}",
                "jane.doe@example.org");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contactNo\":1,\"name\":\"Name\",\"gender\":\"Gender\","
                                        + "\"address\":{\"addressID\":null,\"houseNumber\":0,\"buildingName\":null,\"streetName\":null,\"landmark\":null,"
                                        + "\"pinCode\":0,\"city\":null,\"state\":null}}"));
    }

    /**
     * Method under test: {@link UserController#getByEmail(String, HttpSession)}
     */
    @Test
    void testGetByEmail2() throws Exception {
        when(userServiceImpl.findAllUsers()).thenReturn(new ArrayList<>());
        when(userServiceImpl.findByEmail((String) any())).thenThrow(new UserNotFoundException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/{email}", "",
                "Uri Vars");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#register(User)}
     */
    @Test
    void testRegister() throws Exception {
        User user = new User();
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");
        when(userServiceImpl.addUser((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setAddress(new Address());
        user1.setContactNo(1L);
        user1.setEmail("jane.doe@example.org");
        user1.setGender("Gender");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contactNo\":1,\"name\":\"Name\",\"gender\":\"Gender\","
                                        + "\"address\":{\"addressID\":null,\"houseNumber\":0,\"buildingName\":null,\"streetName\":null,\"landmark\":null,"
                                        + "\"pinCode\":0,\"city\":null,\"state\":null}}"));
    }

    /**
     * Method under test: {@link UserController#updateUser(User, String)}
     */
    @Test
    void testUpdateUser() throws Exception {
        User user = new User();
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");
        when(userServiceImpl.UpdateByEmail((User) any(), (String) any())).thenReturn(user);

        User user1 = new User();
        user1.setAddress(new Address());
        user1.setContactNo(1L);
        user1.setEmail("jane.doe@example.org");
        user1.setGender("Gender");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/users/update/{email}", "jane.doe@example.org")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"contactNo\":1,\"name\":\"Name\",\"gender\":\"Gender\","
                                        + "\"address\":{\"addressID\":null,\"houseNumber\":0,\"buildingName\":null,\"streetName\":null,\"landmark\":null,"
                                        + "\"pinCode\":0,\"city\":null,\"state\":null}}"));
    }
}

