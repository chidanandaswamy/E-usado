package com.stackroute.orderservice.controller;


import com.stackroute.orderservice.model.Order;
import com.stackroute.orderservice.service.OrderServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Order order;

    private OrderServiceImpl orderServiceImpl;
    @InjectMocks
    private OrderController orderController;


    private List<Order> orders;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        orders = new Order(long.fromString("50"),buyerEmail:"qwe@gmail.com",orderDate:"12"



                )
    }
}
