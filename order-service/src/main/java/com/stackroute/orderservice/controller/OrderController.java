package com.stackroute.orderservice.controller;

import com.stackroute.orderservice.model.Order;
import com.stackroute.orderservice.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private ResponseEntity responseEntity;
    @Autowired
    OrderServiceImpl orderService;

    @PostMapping("/addOrder")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        order.setId(orderService.getSequenceNumber(Order.SEQUENCE_NAME));
        return orderService.createOrder(order);
    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/getOrders")
    public ResponseEntity<?> getAllOrders() {
        responseEntity = new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<String> updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable String id) {
        return orderService.deleteOrderById(id);

    }

    @DeleteMapping("/deleteAllOrders")
    public ResponseEntity<?> deleteAll(Order order) {
        responseEntity = new ResponseEntity<String>(orderService.deleteAll(order), HttpStatus.ACCEPTED);
        return responseEntity;
    }

}

