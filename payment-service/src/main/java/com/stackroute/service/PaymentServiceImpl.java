package com.stackroute.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stackroute.model.Payment;
import com.stackroute.repository.PaymentRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public ResponseEntity<?> createOrder(Double amount) {
        try {
            RazorpayClient client = new RazorpayClient("rzp_test_TSA8K6bFoZcQxj","Y3KCxLSQ4BOlOlbzpm5oZPwr");

            JSONObject options = new JSONObject();
            options.put("amount", amount);
            options.put("currency", "INR");
            options.put("receipt", "txn_123456");

            Order order = client.orders.create(options);

            System.out.println(order);

            //save order details into database
            Payment payment = new Payment();
            payment.setAmount(order.get("amount"));
            payment.setOrderId(order.get("id"));
            payment.setReceipt(order.get("receipt"));
            payment.setStatus(order.get("status"));

            paymentRepository.save(payment);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> updatePayment(Payment payment) {

        Payment savedPayment = paymentRepository.findByOrderId(payment.getOrderId());
        savedPayment.setPaymentId(payment.getPaymentId());
        savedPayment.setRazorpaySignature(payment.getRazorpaySignature());
        savedPayment.setStatus(payment.getStatus());

        paymentRepository.save(savedPayment);

        return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getPayments() {
        return new ResponseEntity<>(paymentRepository.findAll(), HttpStatus.OK);
    }
}
