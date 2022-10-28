//package com.stackroute.userservice.controller;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
////
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.userservice.model.Address;
//import com.stackroute.userservice.model.User;
//import com.stackroute.userservice.service.UserService;
//import com.stackroute.userservice.service.UserServiceImpl;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//public class UserControllerTest {
//
//    @Autowired
//    private UserController userController;
//
//    @MockBean
//    private UserServiceImpl userServiceImpl;
//
//    private User user;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    UserService userService;
//    @InjectMocks
//    UserController userController;
//
//    @Test
//    public void deleteUserSuccess() throws Exception {
//        when(userService.deleteUserByEmail("ashu@gmail.com")).thenReturn(true);
//        mockMvc.perform(delete("/user/Jhon123")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
////
//
//    @Test
//    public void deleteUserFailure() throws Exception {
//        when(userService.deleteUser("Jhon123")).thenThrow(UserNotFoundException.class);
//        mockMvc.perform(delete("/api/v1/user/Jhon123")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//}
