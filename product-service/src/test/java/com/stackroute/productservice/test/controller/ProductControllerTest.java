package com.stackroute.productservice.test.controller;

import com.alibaba.fastjson2.JSON;
import com.stackroute.productservice.controller.ProductController;
import com.stackroute.productservice.exception.ProductNotFoundException;
import com.stackroute.productservice.model.Location;
import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Product product;
    @MockBean
    private Location location;
    @MockBean
    private ProductServiceImpl productServiceImpl;
    @InjectMocks
    private ProductController productController;
    private List<Product> products;


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        location = new Location("mangalore", new Double[]{-88.00, -90.90});

        product = new Product("fd449384-4a12-11ed-883d-155c4e30b5e7",
                "samsung mobile update second time",
                25000.00,
                "samsung",
                "tv",
                "lg best quality refrigerator",
                new HashMap<String, String>(){{
                    put("motorSpeed","1000rpm");
                }},
                "2015",
                1475484883L,
                "xyz@abc.com",
                0.0F,
                true,
                35.0F,
                location,
                null,
                true,
                1475484883L);

        products = new ArrayList<>();
        products.add(product);

    }


    @Test
    public void createProductSuccess() throws Exception {
        when(productServiceImpl.createProduct(JSON.toJSONString(product), null)).thenReturn(new ResponseEntity<>("Product added successfully.", HttpStatus.CREATED));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product-service/product").contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(JSON.toJSONString(product)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void createProductFailure() throws Exception {
        when(productServiceImpl.createProduct(JSON.toJSONString(product), null)).thenReturn(new ResponseEntity<>("Could not create product.", HttpStatus.INTERNAL_SERVER_ERROR));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product-service/product").contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(JSON.toJSONString(product)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void deleteProductSuccess() throws Exception {

        when(productServiceImpl.deleteProductById(product.getProductId())).thenReturn(new ResponseEntity<>("Product with id " + product.getProductId() + " deleted successfully", HttpStatus.OK));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/product-service/product/fd449384-4a12-11ed-883d-155c4e30b5e7")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void deleteProductFailure() throws Exception {

        when(productServiceImpl.deleteProductById(product.getProductId())).thenReturn(new ResponseEntity<>("Product with id " + product.getProductId() + " is not found.", HttpStatus.NOT_FOUND));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/product-service/product/fd449384-4a12-11ed-883d-155c4e30b5e7")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateProductSuccess() throws Exception {

        when(productServiceImpl.updateProductById(product.getProductId(), JSON.toJSONString(product), null)).thenReturn(new ResponseEntity<>("Product with id " + product.getProductId() + " updated successfully", HttpStatus.OK));
        product.setProductDescription("Mumbai Indians vs RCB match game cd");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/product-service/product/fd449384-4a12-11ed-883d-155c4e30b5e7")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(JSON.toJSONString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void updateProductFailure() throws Exception {

        when(productServiceImpl.updateProductById(product.getProductId(), JSON.toJSONString(product), null)).thenReturn(new ResponseEntity<>("Product with id " + product.getProductId() + " is not found.", HttpStatus.NOT_FOUND));
        product.setProductDescription("Mumbai Indians vs RCB match game cd");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/product-service/product/fd449384-4a12-11ed-883d-155c4e30b5e7")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(JSON.toJSONString(product)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateProductInternalFailure() throws Exception {

        when(productServiceImpl.updateProductById(product.getProductId(), JSON.toJSONString(product), null)).thenReturn(new ResponseEntity<>("Product with id " + product.getProductId() + " is not found.", HttpStatus.INTERNAL_SERVER_ERROR));
        product.setProductDescription("Mumbai Indians vs RCB match game cd");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/product-service/product/fd449384-4a12-11ed-883d-155c4e30b5e7")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(JSON.toJSONString(product)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getProductByIdSuccess() throws Exception {

        when(productServiceImpl.getProductById(product.getProductId())).thenReturn(new ResponseEntity<>(product, HttpStatus.OK));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product-service/product/fd449384-4a12-11ed-883d-155c4e30b5e7")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getProductByIdFailure() throws Exception {

        when(productServiceImpl.getProductById(product.getProductId())).thenThrow(ProductNotFoundException.class);;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product-service/product/fd449384-4a12-11ed-883d-155c4e30b5e7")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
//
//    @Test
//    public void getAllNotesByUserIdSuccess() throws Exception {
//        when(noteService.getAllNoteByUserId("Jhon123")).thenReturn(noteList);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/note/Jhon123")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void getAllNotesByUserIdFailure() throws Exception {
//        when(noteService.getAllNoteByUserId("Jhon123")).thenReturn(null);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/note/Jhon123")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    private static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
