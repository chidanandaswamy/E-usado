package com.stackroute.userservice.controller;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.Address;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import com.stackroute.userservice.service.UserService;
import com.stackroute.userservice.service.UserServiceImpl;
import org.junit.Test;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserServiceImpl userServiceImpl;

    private User user;

     @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;



//	@AfterEach
//	public void destroy()
//	{
//		user=null;
//		address=null;
//		role=null;
//
//
//	}


//    @Test
//    void testDeleteSuccess() throws Exception {
//
//        user = new User();
//        user.setName("Ashu");
//        user.setPassword("ashutosh@123");
//        user.setGender("MALE");
//        user.setEmail("ashu@gmail.com");
//        user.setPassword("password123");
//        user.setContactNo(9876543210L);
//
//        Address address=new Address();
//        address.setCity("Jeypore");
//        address.setLandmark("Near Public School");
//        address.setBuildingName("SR Appartments");
//        address.setHouseNumber(1);
//        address.setStreetName("A");
//        address.setPinCode(764001);
//        address.setState("Odisha");
//
//
//        when(userService.deleteUserByEmail(any())).thenReturn(User);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/user/{userId}", 123);
//        MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string(
//                        "{\"userId\":123,\"role\":\"BOOKIE\",\"gender\":\"MALE\",\"name\":\"Stallin\",\"email\":\"stallin@gmail.com\",\"password"
//                                + "\":\"password123\",\"contact\":9861690000,\"city\":\"Delhi\"}"));
//    }
@Test
  public void testDeleteFailure() throws Exception {
    when(userServiceImpl.deleteUserByEmail(any())).thenThrow(new UserNotFoundException("?"));
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/users/delete/ashu@gmail.com");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build()
            .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
            .andExpect(MockMvcResultMatchers.content().string("NO USER PRESENT WITH THIS EMAIL"));
}

@Test
public void getByEmail() throws Exception
{
    when(userServiceImpl.findByEmail(user.getEmail())).thenReturn(user);
    mockMvc.perform(get("/api/v1/users/ashu@gmail.com")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());

}
//
//    @Test
//    public void updatealldetailstest() throws Exception
//    {
//        user.setName("23456789");
//        user.setContactNo(2345678l);
////        user.setAddress(new Address("lo", "ki", "th", "or"));
//        when(userServiceImpl.UpdateByEmail(user, user.getEmail())).thenReturn(user);
//        mockMvc.perform(put("/api/v1//users/update/ashu@gmail.com"+user.getEmail())
//                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//
//                .andExpect(status().isUpgradeRequired())
//                .andDo(MockMvcResultHandlers.print());
//
//    }


    @Test
   public void testGetAllSuccess() throws Exception {
        when(userServiceImpl.findAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users");
        MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

   // public static String asJsonString(final Object obj) {
//        try {
//            return new JsonMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//

}
