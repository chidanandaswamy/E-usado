package com.stackroute.paymentservice.repository;

import com.stackroute.paymentservice.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {

    @Query(value="{senderEmail:'?0'}")
    List<Payment> findPaymentBySenderEmail(String senderEmail);

    @Query(value="{receiverEmail:'?0'}")
    List<Payment> findPaymentByReceiverEmail(String receiverEmail);

}
