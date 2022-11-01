package com.stackroute.orderservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.stackroute.orderservice.service.CartServiceImpl;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CartController.class})
@ExtendWith(SpringExtension.class)
class CartControllerTest {
    @Autowired
    private CartController cartController;

    @MockBean
    private CartServiceImpl cartServiceImpl;


    @Test
    void testDeleteCartByOwnerEmail() throws Exception {
        when(cartServiceImpl.deleteCartById((String) any())).thenReturn("42");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/deleteCart/{cartOwnerEmail}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("42"));
    }

    @Test
    void testGetCartByOwnerEmail() throws Exception {
        when(cartServiceImpl.getCartById((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getCart/{cartOwnerEmail}",
                "jane.doe@example.org");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetCartByOwnerEmail2() throws Exception {
        when(cartServiceImpl.getCartById((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/getCart/{cartOwnerEmail}",
                "jane.doe@example.org");
        getResult.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cartController).build().perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

