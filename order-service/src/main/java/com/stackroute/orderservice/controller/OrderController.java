package com.stackroute.orderservice.controller;

import com.stackroute.orderservice.model.Order;
import com.stackroute.orderservice.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;






@RestController

public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    @PostMapping("/add")
    public ResponseEntity<String>createOrder(@RequestBody Order order) {
        order.setId(orderService.getSequenceNumber(Order.SEQUENCE_NAME));
        return orderService.createOrder(order);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        return orderService.getOrderById(id) ;
    }
    @GetMapping("/getOrders")
    public ResponseEntity<?> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateOrder(@RequestBody Order order) {
       return orderService.updateOrder(order);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long id) {
        return orderService.deleteOrderById(id);

    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll(Order order) {
        return orderService.deleteAll(order);
    }



}
