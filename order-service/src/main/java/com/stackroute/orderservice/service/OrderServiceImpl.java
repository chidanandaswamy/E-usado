package com.stackroute.orderservice.service;


import com.stackroute.orderservice.exception.OrderNotFoundException;
import com.stackroute.orderservice.model.DbSequence;
import com.stackroute.orderservice.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.stackroute.orderservice.repository.OrderRepository;


import java.util.*;


@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private MongoOperations mongoOperations;



    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String getSequenceNumber(String seqName){
        Query qry=new Query(Criteria.where("id").is(seqName));
        Update update=new Update().inc("seq",1);
        DbSequence counter = mongoOperations
                .findAndModify(qry,update,FindAndModifyOptions.options().returnNew(true).upsert(true),DbSequence.class);
        return !Objects.isNull(counter)?counter.getSeq():String.valueOf(1);
    }


    @Override
    public ResponseEntity<String> createOrder(Order order) {

        Order ordered= orderRepository.save(order);
        if(ordered != null ){
            rabbitTemplate.convertAndSend("Order_exchange","Order_routing key",order);
            return new ResponseEntity<>("Order is added successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Order Creation terminated.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Order> getAllOrders() {

        List<Order> orderList=orderRepository.findAll();
        if(orderList.isEmpty()){
            throw new OrderNotFoundException("No Order found");
        }
        return orderList;
    }

    @Override
    public ResponseEntity<Order>  getOrderById(String id) {
        Optional<Order> orders = orderRepository.findById(id);
        if(orders.isPresent()){
            return new ResponseEntity<>(orders.get(), HttpStatus.OK);
        } else {
            throw new OrderNotFoundException("Order with id " + id + " is not found.");
        }
    }

    @Override
    public ResponseEntity<String> updateOrder(Order order) {
        orderRepository.save(order);
        return new ResponseEntity<String>("Orders are updated", HttpStatus.ACCEPTED);

    }

    @Override
    public ResponseEntity<String> deleteOrderById(String id) {
        Optional<Order> orders = orderRepository.findById(id);
        if(orders.isPresent()){
            orderRepository.deleteById(id);
            return new ResponseEntity<>("Order with id " + id + " canceled successfully", HttpStatus.OK);
        } else {
            throw new OrderNotFoundException("Order with id " + id + " is not found.");
        }


    }

    @Override
    public String deleteAll(Order order) {
        orderRepository.deleteAll();
        return "Orders are canceled";
    }


}


