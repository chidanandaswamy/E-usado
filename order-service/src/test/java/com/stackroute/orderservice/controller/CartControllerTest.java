package com.stackroute.orderservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.orderservice.model.Cart;
import com.stackroute.orderservice.model.Location;
import com.stackroute.orderservice.service.CartServiceImpl;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CartController.class})
@ExtendWith(SpringExtension.class)
class CartControllerTest {
    @Autowired
    private CartController cartController;

    @MockBean
    private CartServiceImpl cartServiceImpl;

    @Test
    void testCreateCart() throws Exception {
        Cart cart = new Cart();
        cart.setCartOwnerEmail("jane.doe@example.org");
        cart.setLocation(new Location());
        cart.setProductAddedTime(1L);
        cart.setProductAvailability(true);
        cart.setProductBrand("Product Brand");
        cart.setProductCategory("Product Category");
        cart.setProductDamageLevel(10.0f);
        cart.setProductDescription("Product Description");
        cart.setProductDiscount(10.0f);
        cart.setProductId("42");
        cart.setProductManufacturedYear("Product Manufactured Year");
        cart.setProductName("Product Name");
        cart.setProductOwnerEmail("jane.doe@example.org");
        cart.setProductPrice(10.0d);
        cart.setProductPurchasedDate(1L);
        cart.setProductSpecs(new HashMap<>());
        cart.setWarrantyStatus(true);
        when(cartServiceImpl.createCart((Cart) any())).thenReturn(cart);

        Cart cart1 = new Cart();
        cart1.setCartOwnerEmail("jane.doe@example.org");
        cart1.setLocation(new Location());
        cart1.setProductAddedTime(1L);
        cart1.setProductAvailability(true);
        cart1.setProductBrand("Product Brand");
        cart1.setProductCategory("Product Category");
        cart1.setProductDamageLevel(10.0f);
        cart1.setProductDescription("Product Description");
        cart1.setProductDiscount(10.0f);
        cart1.setProductId("42");
        cart1.setProductManufacturedYear("Product Manufactured Year");
        cart1.setProductName("Product Name");
        cart1.setProductOwnerEmail("jane.doe@example.org");
        cart1.setProductPrice(10.0d);
        cart1.setProductPurchasedDate(1L);
        cart1.setProductSpecs(new HashMap<>());
        cart1.setWarrantyStatus(true);
        String content = (new ObjectMapper()).writeValueAsString(cart1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/addToCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cartController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"productId\":\"42\",\"productName\":\"Product Name\",\"productPrice\":10.0,\"productBrand\":\"Product Brand\","
                                        + "\"productCategory\":\"Product Category\",\"productDescription\":\"Product Description\",\"productSpecs\":{},"
                                        + "\"productManufacturedYear\":\"Product Manufactured Year\",\"productPurchasedDate\":1,\"productOwnerEmail\":"
                                        + "\"jane.doe@example.org\",\"productDiscount\":10.0,\"warrantyStatus\":true,\"productDamageLevel\":10.0,\"location"
                                        + "\":{\"name\":null,\"coordinates\":null},\"productAvailability\":true,\"productAddedTime\":1,\"cartOwnerEmail\":"
                                        + "\"jane.doe@example.org\"}"));
    }



    @Test
    void testGetCartById() throws Exception {
        Cart cart = new Cart();
        cart.setCartOwnerEmail("jane.doe@example.org");
        cart.setLocation(new Location());
        cart.setProductAddedTime(1L);
        cart.setProductAvailability(true);
        cart.setProductBrand("Product Brand");
        cart.setProductCategory("Product Category");
        cart.setProductDamageLevel(10.0f);
        cart.setProductDescription("Product Description");
        cart.setProductDiscount(10.0f);
        cart.setProductId("42");
        cart.setProductManufacturedYear("Product Manufactured Year");
        cart.setProductName("Product Name");
        cart.setProductOwnerEmail("jane.doe@example.org");
        cart.setProductPrice(10.0d);
        cart.setProductPurchasedDate(1L);
        cart.setProductSpecs(new HashMap<>());
        cart.setWarrantyStatus(true);
        when(cartServiceImpl.getCartById((String) any())).thenReturn(cart);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getCart/{cartOwnerEmail}",
                "jane.doe@example.org");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"productId\":\"42\",\"productName\":\"Product Name\",\"productPrice\":10.0,\"productBrand\":\"Product Brand\","
                                        + "\"productCategory\":\"Product Category\",\"productDescription\":\"Product Description\",\"productSpecs\":{},"
                                        + "\"productManufacturedYear\":\"Product Manufactured Year\",\"productPurchasedDate\":1,\"productOwnerEmail\":"
                                        + "\"jane.doe@example.org\",\"productDiscount\":10.0,\"warrantyStatus\":true,\"productDamageLevel\":10.0,\"location"
                                        + "\":{\"name\":null,\"coordinates\":null},\"productAvailability\":true,\"productAddedTime\":1,\"cartOwnerEmail\":"
                                        + "\"jane.doe@example.org\"}"));
    }



    @Test
    void testDeleteCartById() throws Exception {
        when(cartServiceImpl.deleteCartById((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/deleteCart/{cartOwnerEmail}", "jane.doe@example.org");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }



    @Test
    void testDeleteCartById2() throws Exception {
        when(cartServiceImpl.deleteCartById((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/deleteCart/{cartOwnerEmail}",
                "jane.doe@example.org");
        deleteResult.characterEncoding("Encoding");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }
}

