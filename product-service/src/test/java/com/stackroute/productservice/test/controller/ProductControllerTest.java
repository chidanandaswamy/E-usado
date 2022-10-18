package com.stackroute.productservice.test.controller;

import com.stackroute.productservice.controller.ProductController;
import com.stackroute.productservice.model.Location;
import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.repository.ProductRepository;
import com.stackroute.productservice.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductServiceImpl productServiceImpl;

    Product product1 = new Product(UUID.fromString("fd449384-4a12-11ed-883d-155c4e30b5e7"),
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
            new Location("mangalore", new Double[]{-88.00, -90.90}),
            null,
            true,
            1475484883L);

    Product product2 = new Product(UUID.fromString("fd449384-4a12-11ed-883d-155c4e30b5e7"),
            "sony light",
            25500.00,
            "sony",
            "light",
            "best light",
            new HashMap<String, String>(){{
                put("motorSpeed","1000rpm");
            }},
            "2015",
            1475484883L,
            "abc@abc.com",
            0.0F,
            true,
            35.0F,
            new Location("mangalore", new Double[]{-88.00, -90.90}),
            null,
            true,
            1475484883L);

    Product product3 = new Product(UUID.fromString("fd449384-4a12-11ed-883d-155c4e30b5e7"),
            "samsung",
            25000.00,
            "samsung",
            "tv",
            "samsung best quality refrigerator",
            new HashMap<String, String>(){{
                put("motorSpeed","1000rpm");
            }},
            "2015",
            1475484883L,
            "xyz@abc.com",
            0.0F,
            true,
            35.0F,
            new Location("mangalore", new Double[]{-88.00, -90.90}),
            null,
            true,
            1475484883L);


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void getAllProductsSuccess() throws Exception{
        List<Product> products = new ArrayList<>(Arrays.asList(product1, product2, product2));

        Mockito.when(productRepository.findAll()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));

    }

    @Test
    public void getProductByIdSuccess() throws Exception{

        Mockito.when(productRepository.findById(UUID.fromString("fd449384-4a12-11ed-883d-155c4e30b5e7"))).thenReturn(Optional.of(product1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/product-service/product/fd449384-4a12-11ed-883d-155c4e30b5e7")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()));

    }
}
