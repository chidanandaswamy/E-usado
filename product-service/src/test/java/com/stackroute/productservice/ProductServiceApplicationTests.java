package com.stackroute.productservice;

import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.repository.ProductRepository;
import com.stackroute.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ProductServiceApplicationTests {

	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

}
