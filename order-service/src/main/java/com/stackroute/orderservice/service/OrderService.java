package com.stackroute.orderservice.service;

import com.stackroute.orderservice.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public interface OrderService {

    ResponseEntity<String>createOrder(Order order);
    List<Order> getAllOrders();

    ResponseEntity<Order>  getOrderById(String id);

    ResponseEntity<String> updateOrder(Order order);

    ResponseEntity<String> deleteOrderById(String id);

    String  deleteAll(Order order);
}


