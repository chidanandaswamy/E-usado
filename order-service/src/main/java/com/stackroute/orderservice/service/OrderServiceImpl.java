package com.stackroute.orderservice.service;



import com.stackroute.orderservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.orderservice.repository.OrderRepository;


import java.util.HashSet;



@Service
public class OrderServiceImpl implements OrderService {

    HashSet<Order> orderlist = new HashSet();
    @Autowired
    OrderRepository orderRepository;


    @Override
    public Boolean createOrder(Order order) {

        Order ordered= orderRepository.save(order);
        if(ordered != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public HashSet<Order> getAll() {
        orderRepository.findAll().forEach(orders->orderlist.add(orders));
    return orderlist;
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Boolean updateOrder(Order order) {
        Order updated= orderRepository.save(order);
        if(updated != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void deleteOrderById(long id) {
        orderRepository.deleteById(id);

    }

    @Override
    public void deleteAll(Order order) {
        orderRepository.deleteAll();
    }


}


