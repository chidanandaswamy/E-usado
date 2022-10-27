package com.stackroute.repository;

import com.stackroute.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    @Query(value = "SELECT * FROM payment WHERE orderId = ?0")
    Payment findByOrderId(String orderId);
}
