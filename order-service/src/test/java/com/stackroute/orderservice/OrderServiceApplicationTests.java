package com.stackroute.orderservice;

import com.stackroute.orderservice.controller.OrderController;
import com.stackroute.orderservice.repository.OrderRepository;
import com.stackroute.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(MockitoJUnitRunner.class)
class OrderServiceApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private OrderService orderService;

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderController orderController;
	@Test
	void contextLoads() {
	}

}
