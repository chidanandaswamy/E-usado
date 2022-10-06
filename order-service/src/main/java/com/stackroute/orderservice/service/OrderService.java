package com.stackroute.orderservice.service;

import com.stackroute.orderservice.model.Order;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.UUID;


@Service
public interface OrderService {

    Boolean createOrder(Order order);
    HashSet<Order> getAll();

    Order getOrderById(long id);

    Boolean updateOrder(Order order);

    void deleteOrderById(long id);

    void deleteAll(Order order);
}


