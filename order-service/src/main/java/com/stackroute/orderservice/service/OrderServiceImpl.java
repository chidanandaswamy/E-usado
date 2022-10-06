package com.stackroute.orderservice.service;



import com.stackroute.orderservice.exception.OrderNotFoundException;
import com.stackroute.orderservice.model.DbSequence;
import com.stackroute.orderservice.model.Order;
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


import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {

    HashSet<Order> orderlist = new HashSet();
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private MongoOperations mongoOperations;

    public int getSequenceNumber(String sequenceName){
        Query query=new Query(Criteria.where("id").is(sequenceName));
        Update update=new Update().inc("seq", 1);
        DbSequence counter = mongoOperations.findAndModify(query,update, FindAndModifyOptions.options().returnNew(true).upsert(true),DbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq():1;
    }


    @Override
    public ResponseEntity<String> createOrder(Order order) {

        Order ordered= orderRepository.save(order);
        if(ordered != null ){
            return new ResponseEntity<>("Order is added successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Order Creation terminated.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?>  getAllOrders() {
        List<Order> products = orderRepository.findAll();

        if(products != null && products.size() > 0){
            return new ResponseEntity<List<Order>>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No products found", HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Order>  getOrderById(long id) {
        Optional<Order> productOptional = orderRepository.findById(id);
        if(productOptional.isPresent()){
            return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
        } else {
            throw new OrderNotFoundException("Product with id " + id + " is not found.");
        }
    }

    @Override
    public ResponseEntity<String> updateOrder(Order order) {
        orderRepository.save(order);
        return new ResponseEntity<String>("Orders are updated", HttpStatus.ACCEPTED);

    }

    @Override
    public ResponseEntity<String> deleteOrderById(long id) {
        Optional<Order> productOptional = orderRepository.findById(id);
        if(productOptional.isPresent()){
            orderRepository.deleteById(id);
            return new ResponseEntity<>("Product with id " + id + " deleted successfull", HttpStatus.OK);
        } else {
            throw new OrderNotFoundException("Product with id " + id + " is not found.");
        }


    }

    @Override
    public ResponseEntity<?> deleteAll(Order order) {
        orderRepository.deleteAll();
        return new ResponseEntity<>("Orders are deleted", HttpStatus.ACCEPTED);
    }


}


