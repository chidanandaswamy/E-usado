package com.stackroute.orderservice.service;

import com.stackroute.orderservice.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
public interface OrderService {

    ResponseEntity<String>createOrder(Order order);
    HashSet<Order> getAllOrders();

    ResponseEntity<Order>  getOrderById(long id);

    ResponseEntity<String> updateOrder(Order order);

    ResponseEntity<String> deleteOrderById(long id);

    ResponseEntity<?>  deleteAll(Order order);
}


