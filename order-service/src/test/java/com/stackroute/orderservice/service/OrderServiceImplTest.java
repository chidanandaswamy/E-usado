package com.stackroute.orderservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.orderservice.exception.OrderNotFoundException;
import com.stackroute.orderservice.model.DbSequence;
import com.stackroute.orderservice.model.Order;
import com.stackroute.orderservice.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {
    @MockBean
    private MongoOperations mongoOperations;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @MockBean
    private RabbitTemplate rabbitTemplate;


    @Test
    void testGetSequenceNumber() {
        DbSequence dbSequence = new DbSequence();
        dbSequence.setId("42");
        dbSequence.setSeq("Seq");
        when(mongoOperations.findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DbSequence>) any())).thenReturn(dbSequence);
        assertEquals("Seq", orderServiceImpl.getSequenceNumber("Seq Name"));
        verify(mongoOperations).findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DbSequence>) any());
    }


    @Test
    void testGetSequenceNumber2() {
        DbSequence dbSequence = mock(DbSequence.class);
        when(dbSequence.getSeq()).thenReturn("Seq");
        doNothing().when(dbSequence).setId((String) any());
        doNothing().when(dbSequence).setSeq((String) any());
        dbSequence.setId("42");
        dbSequence.setSeq("Seq");
        when(mongoOperations.findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DbSequence>) any())).thenReturn(dbSequence);
        assertEquals("Seq", orderServiceImpl.getSequenceNumber("Seq Name"));
        verify(mongoOperations).findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DbSequence>) any());
        verify(dbSequence).getSeq();
        verify(dbSequence).setId((String) any());
        verify(dbSequence).setSeq((String) any());
    }

    @Test
    void testCreateOrder() throws AmqpException {
        Order order = new Order();
        order.setBuyerEmail("jane.doe@example.org");
        order.setId("42");
        order.setOrderDate("2020-03-01");
        order.setOrderStatus("Order Status");
        order.setPaymentStatus("Payment Status");
        order.setProductBrand("Product Brand");
        order.setProductName("Product Name");
        order.setProductPrice(10.0d);
        order.setTotalAmount(10.0d);
        when(orderRepository.save((Order) any())).thenReturn(order);
        doNothing().when(rabbitTemplate).convertAndSend((String) any(), (String) any(), (Object) any());

        Order order1 = new Order();
        order1.setBuyerEmail("jane.doe@example.org");
        order1.setId("42");
        order1.setOrderDate("2020-03-01");
        order1.setOrderStatus("Order Status");
        order1.setPaymentStatus("Payment Status");
        order1.setProductBrand("Product Brand");
        order1.setProductName("Product Name");
        order1.setProductPrice(10.0d);
        order1.setTotalAmount(10.0d);
        ResponseEntity<String> actualCreateOrderResult = orderServiceImpl.createOrder(order1);
        assertEquals("Order is added successfully.", actualCreateOrderResult.getBody());
        assertEquals(HttpStatus.CREATED, actualCreateOrderResult.getStatusCode());
        assertTrue(actualCreateOrderResult.getHeaders().isEmpty());
        verify(orderRepository).save((Order) any());
        verify(rabbitTemplate).convertAndSend((String) any(), (String) any(), (Object) any());
    }


    @Test
    void testCreateOrder2() throws AmqpException {
        Order order = new Order();
        order.setBuyerEmail("jane.doe@example.org");
        order.setId("42");
        order.setOrderDate("2020-03-01");
        order.setOrderStatus("Order Status");
        order.setPaymentStatus("Payment Status");
        order.setProductBrand("Product Brand");
        order.setProductName("Product Name");
        order.setProductPrice(10.0d);
        order.setTotalAmount(10.0d);
        when(orderRepository.save((Order) any())).thenReturn(order);
        doThrow(new OrderNotFoundException("Order_exchange")).when(rabbitTemplate)
                .convertAndSend((String) any(), (String) any(), (Object) any());

        Order order1 = new Order();
        order1.setBuyerEmail("jane.doe@example.org");
        order1.setId("42");
        order1.setOrderDate("2020-03-01");
        order1.setOrderStatus("Order Status");
        order1.setPaymentStatus("Payment Status");
        order1.setProductBrand("Product Brand");
        order1.setProductName("Product Name");
        order1.setProductPrice(10.0d);
        order1.setTotalAmount(10.0d);
        assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.createOrder(order1));
        verify(orderRepository).save((Order) any());
        verify(rabbitTemplate).convertAndSend((String) any(), (String) any(), (Object) any());
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.getAllOrders());
        verify(orderRepository).findAll();
    }

    @Test
    void testGetAllOrders2() {
        Order order = new Order();
        order.setBuyerEmail("jane.doe@example.org");
        order.setId("42");
        order.setOrderDate("2020-03-01");
        order.setOrderStatus("No Order found");
        order.setPaymentStatus("No Order found");
        order.setProductBrand("No Order found");
        order.setProductName("No Order found");
        order.setProductPrice(10.0d);
        order.setTotalAmount(10.0d);

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order);
        when(orderRepository.findAll()).thenReturn(orderList);
        List<Order> actualAllOrders = orderServiceImpl.getAllOrders();
        assertSame(orderList, actualAllOrders);
        assertEquals(1, actualAllOrders.size());
        verify(orderRepository).findAll();
    }


    @Test
    void testGetAllOrders3() {
        when(orderRepository.findAll()).thenThrow(new OrderNotFoundException("No Order found"));
        assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.getAllOrders());
        verify(orderRepository).findAll();
    }


    @Test
    void testGetOrderById() {
        Order order = new Order();
        order.setBuyerEmail("jane.doe@example.org");
        order.setId("42");
        order.setOrderDate("2020-03-01");
        order.setOrderStatus("Order Status");
        order.setPaymentStatus("Payment Status");
        order.setProductBrand("Product Brand");
        order.setProductName("Product Name");
        order.setProductPrice(10.0d);
        order.setTotalAmount(10.0d);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findById((String) any())).thenReturn(ofResult);
        ResponseEntity<Order> actualOrderById = orderServiceImpl.getOrderById("42");
        assertTrue(actualOrderById.hasBody());
        assertTrue(actualOrderById.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualOrderById.getStatusCode());
        verify(orderRepository).findById((String) any());
    }

    @Test
    void testGetOrderById2() {
        when(orderRepository.findById((String) any())).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.getOrderById("42"));
        verify(orderRepository).findById((String) any());
    }


    @Test
    void testGetOrderById3() {
        when(orderRepository.findById((String) any())).thenThrow(new OrderNotFoundException("foo"));
        assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.getOrderById("42"));
        verify(orderRepository).findById((String) any());
    }


    @Test
    void testUpdateOrder() {
        Order order = new Order();
        order.setBuyerEmail("jane.doe@example.org");
        order.setId("42");
        order.setOrderDate("2020-03-01");
        order.setOrderStatus("Order Status");
        order.setPaymentStatus("Payment Status");
        order.setProductBrand("Product Brand");
        order.setProductName("Product Name");
        order.setProductPrice(10.0d);
        order.setTotalAmount(10.0d);
        when(orderRepository.save((Order) any())).thenReturn(order);

        Order order1 = new Order();
        order1.setBuyerEmail("jane.doe@example.org");
        order1.setId("42");
        order1.setOrderDate("2020-03-01");
        order1.setOrderStatus("Order Status");
        order1.setPaymentStatus("Payment Status");
        order1.setProductBrand("Product Brand");
        order1.setProductName("Product Name");
        order1.setProductPrice(10.0d);
        order1.setTotalAmount(10.0d);
        ResponseEntity<String> actualUpdateOrderResult = orderServiceImpl.updateOrder(order1);
        assertEquals("Orders are updated", actualUpdateOrderResult.getBody());
        assertEquals(HttpStatus.ACCEPTED, actualUpdateOrderResult.getStatusCode());
        assertTrue(actualUpdateOrderResult.getHeaders().isEmpty());
        verify(orderRepository).save((Order) any());
    }


    @Test
    void testUpdateOrder2() {
        when(orderRepository.save((Order) any())).thenThrow(new OrderNotFoundException("Orders are updated"));

        Order order = new Order();
        order.setBuyerEmail("jane.doe@example.org");
        order.setId("42");
        order.setOrderDate("2020-03-01");
        order.setOrderStatus("Order Status");
        order.setPaymentStatus("Payment Status");
        order.setProductBrand("Product Brand");
        order.setProductName("Product Name");
        order.setProductPrice(10.0d);
        order.setTotalAmount(10.0d);
        assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.updateOrder(order));
        verify(orderRepository).save((Order) any());
    }


    @Test
    void testDeleteOrderById() {
        Order order = new Order();
        order.setBuyerEmail("jane.doe@example.org");
        order.setId("42");
        order.setOrderDate("2020-03-01");
        order.setOrderStatus("Order Status");
        order.setPaymentStatus("Payment Status");
        order.setProductBrand("Product Brand");
        order.setProductName("Product Name");
        order.setProductPrice(10.0d);
        order.setTotalAmount(10.0d);
        Optional<Order> ofResult = Optional.of(order);
        doNothing().when(orderRepository).deleteById((String) any());
        when(orderRepository.findById((String) any())).thenReturn(ofResult);
        ResponseEntity<String> actualDeleteOrderByIdResult = orderServiceImpl.deleteOrderById("42");
        assertEquals("Order with id 42 canceled successfully", actualDeleteOrderByIdResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteOrderByIdResult.getStatusCode());
        assertTrue(actualDeleteOrderByIdResult.getHeaders().isEmpty());
        verify(orderRepository).findById((String) any());
        verify(orderRepository).deleteById((String) any());
    }


    @Test
    void testDeleteOrderById2() {
        Order order = new Order();
        order.setBuyerEmail("jane.doe@example.org");
        order.setId("42");
        order.setOrderDate("2020-03-01");
        order.setOrderStatus("Order Status");
        order.setPaymentStatus("Payment Status");
        order.setProductBrand("Product Brand");
        order.setProductName("Product Name");
        order.setProductPrice(10.0d);
        order.setTotalAmount(10.0d);
        Optional<Order> ofResult = Optional.of(order);
        doThrow(new OrderNotFoundException("foo")).when(orderRepository).deleteById((String) any());
        when(orderRepository.findById((String) any())).thenReturn(ofResult);
        assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.deleteOrderById("42"));
        verify(orderRepository).findById((String) any());
        verify(orderRepository).deleteById((String) any());
    }


    @Test
    void testDeleteOrderById3() {
        doNothing().when(orderRepository).deleteById((String) any());
        when(orderRepository.findById((String) any())).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.deleteOrderById("42"));
        verify(orderRepository).findById((String) any());
    }


    @Test
    void testDeleteAll() {
        doNothing().when(orderRepository).deleteAll();

        Order order = new Order();
        order.setBuyerEmail("jane.doe@example.org");
        order.setId("42");
        order.setOrderDate("2020-03-01");
        order.setOrderStatus("Order Status");
        order.setPaymentStatus("Payment Status");
        order.setProductBrand("Product Brand");
        order.setProductName("Product Name");
        order.setProductPrice(10.0d);
        order.setTotalAmount(10.0d);
        assertEquals("Orders are canceled", orderServiceImpl.deleteAll(order));
        verify(orderRepository).deleteAll();
    }

    @Test
    void testDeleteAll2() {
        doThrow(new OrderNotFoundException("Orders are canceled")).when(orderRepository).deleteAll();

        Order order = new Order();
        order.setBuyerEmail("jane.doe@example.org");
        order.setId("42");
        order.setOrderDate("2020-03-01");
        order.setOrderStatus("Order Status");
        order.setPaymentStatus("Payment Status");
        order.setProductBrand("Product Brand");
        order.setProductName("Product Name");
        order.setProductPrice(10.0d);
        order.setTotalAmount(10.0d);
        assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.deleteAll(order));
        verify(orderRepository).deleteAll();
    }
}

