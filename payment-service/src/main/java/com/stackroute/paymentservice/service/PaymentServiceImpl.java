package com.stackroute.paymentservice.service;


import com.fasterxml.uuid.Generators;
import com.stackroute.paymentservice.dto.ResponseDto;
import com.stackroute.paymentservice.model.Payment;
import com.stackroute.paymentservice.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.secret-key}")
    private String secretKey;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public ResponseEntity<?> createPaymentIntent(Payment payment) throws StripeException {

        Stripe.apiKey = secretKey;

        CustomerCreateParams.Address addressParams = CustomerCreateParams.Address.builder()
                .setCountry(payment.getBillingAddress().getCountry())
                .setLine1(payment.getBillingAddress().getLine1())
                .setLine2(payment.getBillingAddress().getLine2())
                .setCity(payment.getBillingAddress().getCity())
                .setState(payment.getBillingAddress().getState())
                .setPostalCode(payment.getBillingAddress().getZipCode()).build();


        CustomerCreateParams customerParams = new CustomerCreateParams.Builder()
                .setEmail(payment.getSenderEmail())
                .setName(payment.getSenderName())
                .setAddress(addressParams).build();

        Customer customer = Customer.create(customerParams);

        if(customer.getId() != null){
            PaymentIntentCreateParams createParams = new
                    PaymentIntentCreateParams.Builder()
                    .setCurrency("INR")
                    .setCustomer(customer.getId())
                    .setDescription(payment.getPaymentDescription())
                    .setAmount(payment.getAmount() * 100l)
                    .build();

            PaymentIntent intent = PaymentIntent.create(createParams);

            if(intent != null){
                payment.setStripeCustomerId(customer.getId());
                payment.setStripePaymentId(intent.getId());
                payment.setPaymentStatus("pending");
                payment.setPaymentId(Generators.timeBasedGenerator().generate().toString());

                paymentRepository.insert(payment);

                return new ResponseEntity<ResponseDto>(new ResponseDto(intent.getClientSecret()), HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Failed to create customer", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<String>("Payment Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @Override
    public ResponseEntity<?> getPaymentsBySenderEmail(String email) {
        List<Payment> payments = paymentRepository.findPaymentBySenderEmail(email);

        if(payments != null && payments.size() > 0){
            return new ResponseEntity<List<Payment>>(payments, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No payments found with this email", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> getPaymentById(String paymentId) {
        Optional<Payment> payment = paymentRepository.findById(paymentId);

        if(payment.isPresent()){
            return new ResponseEntity<Payment>(payment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Payment with id " + paymentId + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> updatePaymentStatus(String paymentId, String status) {
        Optional<Payment> payment = paymentRepository.findById(paymentId);

        if(payment.isPresent()){
            payment.get().setPaymentStatus(status);
            paymentRepository.save(payment.get());
            return new ResponseEntity<String>("Payment update successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Payment with id " + paymentId + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> getPaymentsByReceiverEmail(String email) {
        List<Payment> payments = paymentRepository.findPaymentByReceiverEmail(email);

        if(payments != null && payments.size() > 0){
            return new ResponseEntity<List<Payment>>(payments, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No payments found with this email", HttpStatus.NOT_FOUND);
        }
    }

    public Long getWalletAmount(String email) {
        List<Payment> payments = paymentRepository.findPaymentByReceiverEmail(email);

        if(payments != null && payments.size() > 0){
            return payments.stream().mapToLong(payment -> payment.getAmount()).sum();
        } else {
            return 0l;
        }
    }

}
