package com.stackroute.emailservice.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.emailservice.dto.MailRequest;
import com.stackroute.emailservice.dto.MailResponse;
import com.stackroute.emailservice.model.OrderService;
import com.stackroute.emailservice.model.SlotBooking;
import com.stackroute.emailservice.model.UserService;
import com.stackroute.emailservice.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {EmailController.class})
@ExtendWith(SpringExtension.class)
class EmailControllerTest {
    @Autowired
    private EmailController emailController;

    @MockBean
    private EmailService emailService;


    @Test
    void testProductAddedConforming() throws Exception {
        when(emailService.sendEmailProductAdded((MailRequest) any(), (Map<String, Object>) any()))
                .thenReturn(new MailResponse("Not all who wander are lost", true));

        MailRequest mailRequest = new MailRequest();
        mailRequest.setId(UUID.randomUUID());
        mailRequest.setProductBrand("Product Brand");
        mailRequest.setProductCategory("Product Category");
        mailRequest.setProductDamageLevel(10.0f);
        mailRequest.setProductDescription("Product Description");
        mailRequest.setProductDiscount(10.0f);
        mailRequest.setProductManufacturedYear("Product Manufactured Year");
        mailRequest.setProductName("Product Name");
        mailRequest.setProductOwnerEmail("jane.doe@example.org");
        mailRequest.setProductPrice(10.0d);
        mailRequest.setProductPurchasedDate(1L);
        mailRequest.setProductSpecs(new HashMap<>());
        mailRequest.setWarrantyStatus(true);
        String content = (new ObjectMapper()).writeValueAsString(mailRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ProductAdded")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(emailController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"message\":\"Not all who wander are lost\",\"status\":true}"));
    }


    @Test
    void testSendEmail() throws Exception {
        when(emailService.OrderConformationsendEmail((OrderService) any(), (Map<String, Object>) any()))
                .thenReturn(new MailResponse("Not all who wander are lost", true));

        OrderService orderService = new OrderService();
        orderService.setBuyerEmail("jane.doe@example.org");
        orderService.setId(123L);
        orderService.setOrderDate("2020-03-01");
        orderService.setOrderStatus("Order Status");
        orderService.setPaymentStatus("Payment Status");
        orderService.setProducts("Products");
        orderService.setTotalAmount(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(orderService);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/OrderConformation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(emailController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"message\":\"Not all who wander are lost\",\"status\":true}"));
    }


    @Test
    void testSendEmailSlot() throws Exception {
        when(emailService.sendEmailSlotBooking((SlotBooking) any(), (Map<String, Object>) any()))
                .thenReturn(new MailResponse("Not all who wander are lost", true));

        SlotBooking slotBooking = new SlotBooking();
        slotBooking.setBuyerEmailId("42");
        slotBooking.setBuyerName("Buyer Name");
        slotBooking.setDate("2020-03-01");
        slotBooking.setDateTimeSlots(new ArrayList<>());
        slotBooking.setDescription("The characteristics of someone or something");
        slotBooking.setSellerEmailId("42");
        slotBooking.setSellerName("Seller Name");
        slotBooking.setSlotId(123L);
        String content = (new ObjectMapper()).writeValueAsString(slotBooking);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/slotBookConfirm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(emailController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"message\":\"Not all who wander are lost\",\"status\":true}"));
    }


    @Test
    void testSendEmailThank() throws Exception {
        when(emailService.sendEmailThankyou((UserService) any(), (Map<String, Object>) any()))
                .thenReturn(new MailResponse("Not all who wander are lost", true));

        UserService userService = new UserService();
        userService.setEmail("jane.doe@example.org");
        userService.setName("Name");
        userService.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(userService);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ThankYouForRegister")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(emailController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"message\":\"Not all who wander are lost\",\"status\":true}"));
    }
}

