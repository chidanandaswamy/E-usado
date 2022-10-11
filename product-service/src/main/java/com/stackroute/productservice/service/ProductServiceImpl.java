package com.stackroute.productservice.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.uuid.Generators;
import com.stackroute.productservice.exception.ProductNotFoundException;
import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.repository.ProductRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${e-usado.product-service.rabbitmq.exchange}")
    private String exchange;

    @Value("${e-usado.product.rabbitmq.routingkey}")
    private String routingKey;

    @Override
    public ResponseEntity<String> createProduct(String productAsJSONString, MultipartFile image1) {

        Product product = JSON.parseObject(productAsJSONString, Product.class);
        product.setId(Generators.timeBasedGenerator().generate());
        try {
            product.setProductImage(new Binary(BsonBinarySubType.BINARY, image1.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Product savedProduct = productRepository.save(product);
        if(savedProduct != null && savedProduct.getId() != null){
            rabbitTemplate.convertAndSend(exchange, routingKey, product);
            return new ResponseEntity<>("Product added successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Could not create product.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getProducts(int pageNumber,
                                         int pageSize,
                                         String productBrand,
                                         String productCategory,
                                         String productManufacturedYear,
                                         String warrantyStatus,
                                         BigDecimal productPrice,
                                         Float productDiscount,
                                         Float productDamageLevel,
                                         String location) {
        //pagination
        int offset = 0;
        int limit = 0;

        if(pageNumber == 0){
            pageNumber = 1;
        }

        if(pageSize == 0){
            pageSize = 10;
        }

        limit = pageSize;
        offset = pageSize * (pageNumber - 1);

        //filter
        JSONObject filter = new JSONObject();

        if(productBrand != null && !productBrand.equalsIgnoreCase("all")){
            filter.put("productBrand", productBrand);
        }

        if(productCategory != null && !productCategory.equalsIgnoreCase("all")){
            filter.put("productCategory", productCategory);
        }

        if(productManufacturedYear != null && !productManufacturedYear.equalsIgnoreCase("all")){
            filter.put("productManufacturedYear", productManufacturedYear);
        }

        if(warrantyStatus != null && !warrantyStatus.equalsIgnoreCase("all")){
            filter.put("warrantyStatus", Boolean.valueOf(warrantyStatus));
        }

//        System.out.println("product price" + productPrice);
//        BigDecimal upperLimitPrice;
//        BigDecimal lowerLimitPrice;
//        if(productPrice != null){
//            if(productPrice.compareTo(BigDecimal.valueOf(5000)) == 1){
//                lowerLimitPrice = productPrice.subtract(BigDecimal.valueOf(5000));
//            }
//            upperLimitPrice = productPrice.add(BigDecimal.valueOf(5000));
//        }

//        List<Product> products = productRepository.findProducts(offset, limit);
        System.out.println("filter" + filter);
        Query query = new BasicQuery(filter.toJSONString()).skip(offset).limit(limit);
        query.addCriteria(Criteria.where("productPrice").is(productPrice));
        List<Product> products = mongoTemplate.find(query, Product.class);
        if(products != null && products.size() > 0){
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No products found", HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<Product> getProductById(UUID id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
        } else {
            throw new ProductNotFoundException("Product with id " + id + " is not found.");
        }
    }

    @Override
    public ResponseEntity<String> updateProductById(UUID id, String productAsJSONString, MultipartFile image1) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            Product product = JSON.parseObject(productAsJSONString, Product.class);
            product.setId(id);

            if(!image1.isEmpty()){
                try {
                    product.setProductImage(new Binary(BsonBinarySubType.BINARY, image1.getBytes()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                product.setProductImage(productOptional.get().getProductImage());
            }

            Product savedProduct = productRepository.save(product);
            if(savedProduct != null && savedProduct.getId() != null){
                return new ResponseEntity<>("Product with id " + id + " not found", HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Update of product with id " + id + " failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new ProductNotFoundException("Product with id " + id + " is not found.");
        }
    }

    @Override
    public ResponseEntity<String> deleteProductById(UUID id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            productRepository.deleteById(id);
            return new ResponseEntity<>("Product with id " + id + " deleted successfully", HttpStatus.OK);
        } else {
            throw new ProductNotFoundException("Product with id " + id + " is not found.");
        }
    }

    @Override
    public ResponseEntity<?> getProductsByOwnerEmail(String ownerEmail,
                                                     int pageNumber,
                                                     int pageSize,
                                                     String productBrand) {
        //pagination
        int offset = 0;
        int limit = 0;

        if(pageNumber == 0){
            pageNumber = 1;
        }

        if(pageSize == 0){
            pageSize = 10;
        }

        limit = pageSize;
        offset = pageSize * (pageNumber - 1);

        //filter


        List<Product> products = productRepository.findProductsByOwnerEmail(ownerEmail);
        if(products != null && products.size() > 0){
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("User " + ownerEmail + " has no products", HttpStatus.OK);
        }
    }

    public void test(){
        String filter = "{\"productBrand\":\"LG\"}";
        BasicQuery q = new BasicQuery(filter);
        System.out.println(mongoTemplate.find(q, Product.class));
    }
}
