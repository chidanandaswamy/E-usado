package com.stackroute.orderservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.orderservice.model.Order;
import com.stackroute.orderservice.service.OrderServiceImpl;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
class OrderControllerTest {
    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderServiceImpl orderServiceImpl;


    @Test
    void testCreateOrder() throws Exception {
        when(orderServiceImpl.getSequenceNumber((String) any())).thenReturn("42");
        when(orderServiceImpl.createOrder((Order) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        Order order = new Order();
        order.setBuyerEmail("jane.doe@example.org");
        order.setId("42");
        order.setOrderDate("2020-03-01");
        order.setOrderStatus("Order Status");
        order.setPaymentStatus("Payment Status");
        order.setProductBrand("Product Brand");
        order.setProductName("Product Name");
        order.setProductPrice(10.0d);
        order.setTotalAmount(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/addOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    void testGetOrderById() throws Exception {
        when(orderServiceImpl.getOrderById((String) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getOrder/{id}", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }


    @Test
    void testGetAllOrders() throws Exception {
        when(orderServiceImpl.getAllOrders()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getOrders");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllOrders2() throws Exception {
        when(orderServiceImpl.getAllOrders()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/getOrders");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testUpdateOrder() throws Exception {
        when(orderServiceImpl.updateOrder((Order) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        Order order = new Order();
        order.setBuyerEmail("jane.doe@example.org");
        order.setId("42");
        order.setOrderDate("2020-03-01");
        order.setOrderStatus("Order Status");
        order.setPaymentStatus("Payment Status");
        order.setProductBrand("Product Brand");
        order.setProductName("Product Name");
        order.setProductPrice(10.0d);
        order.setTotalAmount(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/updateOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    void testDeleteOrderById() throws Exception {
        when(orderServiceImpl.deleteOrderById((String) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/deleteOrder/{id}", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }


    @Test
    void testDeleteAll() throws Exception {
        when(orderServiceImpl.deleteAll((Order) any())).thenReturn("Delete All");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/deleteAllOrders");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete All"));
    }
}

