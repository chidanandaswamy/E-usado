package com.stackroute.productservice.test.service;

import com.alibaba.fastjson2.JSON;
import com.stackroute.productservice.exception.ProductNotFoundException;
import com.stackroute.productservice.model.Location;
import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.repository.ProductRepository;
import com.stackroute.productservice.service.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    @MockBean
    private Product product;
    @MockBean
    private Location location;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private MongoTemplate mongoTemplate;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;
    private List<Product> products = null;
    Optional<Product> productOptional;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);


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

        productOptional = Optional.of(product);

    }


    @Test
    public void createProductSuccess() {

        when(productRepository.save(any())).thenReturn(product);
        ResponseEntity<?> response = productServiceImpl.createProduct(JSON.toJSONString(product), null);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        verify(noteRepository, times(1)).insert((NoteUser) any());
    }

    @Test
    public void createProductFailure() {
        when(productRepository.save(any())).thenReturn(null);
        ResponseEntity<?> response = productServiceImpl.createProduct(JSON.toJSONString(product), null);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    public void deleteProductSuccess() {
        when(productRepository.findById(product.getProductId())).thenReturn(productOptional);
        ResponseEntity<?> response = productServiceImpl.deleteProductById(product.getProductId());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = NullPointerException.class)
    public void deleteProductFailure() {
        when(productRepository.findById(product.getProductId())).thenReturn(null);
        ResponseEntity<?> response = productServiceImpl.deleteProductById(product.getProductId());
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    public void updateProductSuccess() throws ProductNotFoundException {

        when(productRepository.findById("fd449384-4a12-11ed-883d-155c4e30b5e7")).thenReturn(productOptional);
        product.setProductDescription("Heavy weight product");
        ResponseEntity<?> response = productServiceImpl.updateProductById(product.getProductId(), JSON.toJSONString(product), null);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = ProductNotFoundException.class)
    public void updateNoteFailure() throws ProductNotFoundException {

        when(productRepository.findById(product.getProductId())).thenThrow(NoSuchElementException.class);
        product.setProductDescription("Heavy weight product");
        ResponseEntity<?> response = productServiceImpl.updateProductById(product.getProductId(), JSON.toJSONString(product), null);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void getProductByIdSuccess() throws ProductNotFoundException {
        when(productRepository.findById(product.getProductId())).thenReturn(productOptional);
        ResponseEntity<?> response = productServiceImpl.getProductById(product.getProductId());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = ProductNotFoundException.class)
    public void getProductByIdFailure() throws ProductNotFoundException {
        when(productRepository.findById(product.getProductId())).thenReturn(null);
        ResponseEntity<?> response = productServiceImpl.getProductById(product.getProductId());
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
//
//    @Test
//    public void getAllNoteByUserId() {
//        when(noteRepository.findById("Jhon123")).thenReturn(options);
//        List<Note> notes = noteServiceImpl.getAllNoteByUserId("Jhon123");
//        Assert.assertEquals(noteList, notes);
//    }
}
