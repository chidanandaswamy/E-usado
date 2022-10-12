package com.stackroute.orderservice.controller;

import com.stackroute.orderservice.model.Order;
import com.stackroute.orderservice.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private ResponseEntity responseEntity;
    @Autowired
    OrderServiceImpl orderService;

    @PostMapping("/addOrder")
    public ResponseEntity<String>createOrder(@RequestBody Order order) {
        order.setId(orderService.getSequenceNumber(Order.SEQUENCE_NAME));
        return orderService.createOrder(order);
    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        return orderService.getOrderById(id) ;
    }
    @GetMapping("/getOrders")
    public HashSet<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<String> updateOrder(@RequestBody Order order) {
       return orderService.updateOrder(order);
    }
    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long id) {
        return orderService.deleteOrderById(id);

    }
    @DeleteMapping("/deleteAllOrders")
    public ResponseEntity<?> deleteAll(Order order) {
        return orderService.deleteAll(order);
    }



}
