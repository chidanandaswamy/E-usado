package com.stackroute.orderservice.controller;

import com.stackroute.orderservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stackroute.orderservice.service.OrderService;

import java.util.HashSet;



@RestController

public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        return new  ResponseEntity<Boolean>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        return new ResponseEntity<Order> (orderService.getOrderById(id), HttpStatus.OK) ;
    }
    @GetMapping("/getAll")
    public HashSet<Order> getAll() {
        return orderService.getAll();
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateOrder(@RequestBody Order order) {
       return new ResponseEntity<Boolean> (orderService.updateOrder(order), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteOrderById(@PathVariable long id) {
        orderService.deleteOrderById(id);

    }
    @DeleteMapping("/deleteAll")
    public void deleteAll(Order order) {
        orderService.deleteAll(order);
    }



}
