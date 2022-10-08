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



import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {


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
        List<Order> orders = orderRepository.findAll();

        if(orders != null){
            return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No Order found", HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Order>  getOrderById(long id) {
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
    public ResponseEntity<String> deleteOrderById(long id) {
        Optional<Order> orders = orderRepository.findById(id);
        if(orders.isPresent()){
            orderRepository.deleteById(id);
            return new ResponseEntity<>("Order with id " + id + " deleted successfull", HttpStatus.OK);
        } else {
            throw new OrderNotFoundException("Order with id " + id + " is not found.");
        }


    }

    @Override
    public ResponseEntity<?> deleteAll(Order order) {
        orderRepository.deleteAll();
        return new ResponseEntity<>("Orders are deleted", HttpStatus.ACCEPTED);
    }


}


