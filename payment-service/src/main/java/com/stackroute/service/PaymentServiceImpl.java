package com.stackroute.service;


import com.stackroute.dto.Payment;
import com.stackroute.dto.ResponseDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Address;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.secret-key}")
    private String secretKey;

    @Override
    public ResponseEntity<ResponseDto> createPaymentIntent(Payment payment) throws StripeException {

        Stripe.apiKey = secretKey;

        CustomerCreateParams.Address addressParams = CustomerCreateParams.Address.builder()
                .setCountry("")
                .setLine1("")
                .setLine2("")
                .setCity("")
                .setState("")
                .setPostalCode("").build();


        CustomerCreateParams customerParams = new CustomerCreateParams.Builder()
                .setEmail("")
                .setName("")
                .setAddress(addressParams).build();

        Customer customer = Customer.create(customerParams);

        PaymentIntentCreateParams createParams = new
                PaymentIntentCreateParams.Builder()
                .setCurrency("INR")
                .setCustomer(customer.getId())
                .setDescription("tesing")
                .putMetadata("featureRequest", payment.getFeatureRequest())
                .setAmount(payment.getAmount() * 1L)
                .build();

        PaymentIntent intent = PaymentIntent.create(createParams);
        System.out.println("intent " + intent.getClientSecret());
        return new ResponseEntity<ResponseDto>(new ResponseDto(intent.getClientSecret()), HttpStatus.OK);
    }
}
