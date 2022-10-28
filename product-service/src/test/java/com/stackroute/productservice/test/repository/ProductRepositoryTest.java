package com.stackroute.productservice.test.repository;

import com.fasterxml.uuid.Generators;
import com.stackroute.productservice.model.Location;
import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.repository.ProductRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    private Product product;
    private Location location;
    private List<Product> products = null;

    private UUID id = Generators.randomBasedGenerator().generate();


    @Before
    public void setUp() throws Exception {

        location = new Location("mangalore", new Double[]{-88.00, -90.90});

        product = new Product(id.toString(),
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

    @After
    public void tearDown() throws Exception {
        productRepository.deleteAll();
    }

    @Test
    public void createProductTest() {
        productRepository.insert(product);
        List<Product> products = productRepository.findAll();
        Assert.assertEquals(this.products.get(0).getProductId(), products.get(0).getProductId());
    }


    @Test
    public void deleteProductTest() {
        productRepository.insert(product);
        List<Product> products = productRepository.findAll();
        Assert.assertEquals(this.products.get(0).getProductId(), products.get(0).getProductId());

        productRepository.deleteById(product.getProductId());

        products = productRepository.findAll();

        Assert.assertEquals(true, products.isEmpty());

    }


    @Test
    public void updateProductTest() {
        productRepository.insert(product);
        List<Product> products = productRepository.findAll();
        Assert.assertEquals(this.products.get(0).getProductId(), products.get(0).getProductId());
        Iterator iterator = products.listIterator();
        while (iterator.hasNext()) {
            product = (Product) iterator.next();

            if (product.getProductId().equals(id))
                product.setProductDescription("lorem ipsum");
        }

        productRepository.save(product);
        products = productRepository.findAll();

        Assert.assertEquals("lorem ipsum", products.get(0).getProductDescription());
    }

    @Test
    public void getAllNotesByUserId() {
        productRepository.insert(product);
        List<Product> products = productRepository.findAll();
        Assert.assertEquals(this.products.get(0).getProductId(), products.get(0).getProductId());
    }
}
