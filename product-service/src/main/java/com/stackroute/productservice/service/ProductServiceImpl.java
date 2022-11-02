package com.stackroute.productservice.service;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.uuid.Generators;
import com.mongodb.BasicDBList;
import com.stackroute.productservice.dto.CartDto;
import com.stackroute.productservice.dto.ProductDto;
import com.stackroute.productservice.exception.ProductNotFoundException;
import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.repository.ProductRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    @Value("${e-usado.product.rabbitmq.chat-routing-key}")
    private String chatRoutingKey;

    @Value("${e-usado.product.rabbitmq.mail-routing-key}")
    private String mailRoutingKey;

    @Value("${e-usado.product.rabbitmq.order-routing-key}")
    private String orderRoutingKey;

    @Override
    public ResponseEntity<String> createProduct(String productAsJSONString, MultipartFile[] images) {

        Product product = JSON.parseObject(productAsJSONString, Product.class);
        product.setProductId(Generators.timeBasedGenerator().generate().toString());
        product.setProductAddedTime(System.currentTimeMillis());

        if(images != null && images.length > 0){
            try {
                Binary[] tempImageBinary = new Binary[images.length];
                int i=0;
                for(MultipartFile image : images){
                    tempImageBinary[i] = new Binary(BsonBinarySubType.BINARY, image.getBytes());
                    i++;
                }
                product.setProductImages(tempImageBinary);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Product savedProduct = productRepository.save(product);

        if(savedProduct != null && savedProduct.getProductId() != null){
            ProductDto productDto = JSON.parseObject(productAsJSONString, ProductDto.class);
            productDto.setId(product.getProductId());
            productDto.setProductAddedTime(product.getProductAddedTime());
            rabbitTemplate.convertAndSend(exchange, mailRoutingKey, product);
            rabbitTemplate.convertAndSend(exchange, chatRoutingKey, productDto);
            return new ResponseEntity<>("Product added successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Could not create product.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getProducts(String search,
                                         int pageNumber,
                                         int pageSize,
                                         String productBrand,
                                         String productCategory,
                                         String productManufacturedYear,
                                         String warrantyStatus,
                                         Double productPrice,
                                         Float productDiscount,
                                         Float productDamageLevel,
                                         String location,
                                         String productAvailability) {
        Query query = new Query();
        BasicDBList and = new BasicDBList();

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

        if(productBrand != null && !productBrand.equalsIgnoreCase("all")){
            Criteria criteria = Criteria.where("productBrand").is(productBrand);
            and.add(criteria.getCriteriaObject());
        }

        if(productCategory != null && !productCategory.equalsIgnoreCase("all")){
            Criteria criteria = Criteria.where("productCategory").is(productCategory);
            and.add(criteria.getCriteriaObject());
        }

        if(productManufacturedYear != null && !productManufacturedYear.equalsIgnoreCase("all")){
            Criteria criteria = Criteria.where("productManufacturedYear").is(productManufacturedYear);
            and.add(criteria.getCriteriaObject());
        }

        if(warrantyStatus != null && !warrantyStatus.equalsIgnoreCase("all")){
            Criteria criteria = Criteria.where("warrantyStatus").is(Boolean.valueOf(warrantyStatus));
            and.add(criteria.getCriteriaObject());
        }

        if(productAvailability != null && !productAvailability.equalsIgnoreCase("all")){
            Criteria criteria = Criteria.where("productAvailability").is(Boolean.valueOf(productAvailability));
            and.add(criteria.getCriteriaObject());
        }

        if(location != null && !location.equalsIgnoreCase("all")){
            Criteria criteria = Criteria.where("location.name").is(location);
            and.add(criteria.getCriteriaObject());
        }

        if(productPrice != null && productPrice > -1){
            Double upperLimitPrice = null;
            Double lowerLimitPrice = null;
            if(productPrice - 5000 > 0){
                lowerLimitPrice = productPrice - 5000;
            } else {
                lowerLimitPrice = 0.0;
            }
            upperLimitPrice = productPrice + 5000;

            Criteria criteria = Criteria.where("productPrice").gte(lowerLimitPrice).lte(upperLimitPrice);
            and.add(criteria.getCriteriaObject());
        }

        if(productDiscount != null && productDiscount > -1f){
            Float upperLimit = null;
            Float lowerLimit = null;
            if(productDiscount > 3f){
                lowerLimit = productDiscount - 3f;
            } else {
                lowerLimit = 0f;
            }
            upperLimit = productDiscount + 3f;

            Criteria criteria = Criteria.where("productDiscount").gte(lowerLimit).lte(upperLimit);
            and.add(criteria.getCriteriaObject());
        }

        if(productDamageLevel != null && productDamageLevel > -1f){
            Float upperLimit = null;
            Float lowerLimit = null;
            if(productDamageLevel > 3f){
                lowerLimit = productDamageLevel - 3f;
            } else {
                lowerLimit = 0f;
            }
            upperLimit = productDamageLevel + 3f;

            Criteria criteria = Criteria.where("productDamageLevel").gte(lowerLimit).lte(upperLimit);
            and.add(criteria.getCriteriaObject());
        }

//        List<Product> products = productRepository.findProducts(offset, limit);

        //search products
        if(search != null && !search.equalsIgnoreCase("none")){
            TextCriteria searchCriteria = TextCriteria.forDefaultLanguage().matchingAny(search);
            and.add(searchCriteria.getCriteriaObject());
        }



        if(and.stream().count() > 0){
            query.addCriteria(new Criteria("$and").is(and));
        }

        query.skip(offset).limit(limit);

        List<Product> products = mongoTemplate.find(query, Product.class);

        if(products != null && products.size() > 0){
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No products found", HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<Product> getProductById(String id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
        } else {
            throw new ProductNotFoundException("Product with id " + id + " is not found.");
        }
    }

    @Override
    public ResponseEntity<String> updateProductById(String id, String productAsJSONString, MultipartFile[] images) {
        Optional<Product> productOptional = productRepository.findById(id);
        System.out.println(productOptional.isPresent());
        if(productOptional.isPresent()){
            Product product = JSON.parseObject(productAsJSONString, Product.class);
            product.setProductId(id);

            if(images != null && images.length > 0){
                try {
                    Binary[] tempImageBinary = new Binary[images.length];
                    int i=0;
                    for(MultipartFile image : images){
                        tempImageBinary[i] = new Binary(BsonBinarySubType.BINARY, image.getBytes());
                        i++;
                    }
                    product.setProductImages(tempImageBinary);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                product.setProductImages(productOptional.get().getProductImages());
            }

            Product savedProduct = productRepository.save(product);
            if(savedProduct != null && savedProduct.getProductId() != null){
                return new ResponseEntity<>("Product with id " + id + " updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Update of product with id " + id + " failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new ProductNotFoundException("Product with id " + id + " is not found.");
        }
    }

    @Override
    public ResponseEntity<String> deleteProductById(String id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            productRepository.deleteById(id);
            return new ResponseEntity<>("Product with id " + id + " deleted successfully", HttpStatus.OK);
        } else {
            throw new ProductNotFoundException("Product with id " + id + " is not found.");
        }
    }

    @Override
    public ResponseEntity<String> deleteAllProducts() {
        productRepository.deleteAll();
        return new ResponseEntity<>("ALl the products deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getProductsByOwnerEmail(String ownerEmail,
                                                     int pageNumber,
                                                     int pageSize,
                                                     Long productAddedTime) {
        Query query = new Query();
        BasicDBList and = new BasicDBList();

        //pagination
        int offset = pageSize * (pageNumber - 1);
        int limit = pageSize;

        {
            Criteria criteria = Criteria.where("productOwnerEmail").is(ownerEmail);
            and.add(criteria.getCriteriaObject());
        }

        if(productAddedTime != null && productAddedTime > 0){
            Criteria criteria = Criteria.where("productAddedTime").is(productAddedTime);
            and.add(criteria.getCriteriaObject());
        }

        if(and.stream().count() > 0){
            query.addCriteria(new Criteria("$and").is(and));
        }

        query.skip(offset).limit(limit);

        List<Product> products = mongoTemplate.find(query, Product.class);
        if(products != null && products.size() > 0){
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("User " + ownerEmail + " has no products", HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> addProductToCart(String id, String email){
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            CartDto cartDto = JSON.parseObject(JSON.toJSONString(productOptional.get()), CartDto.class);
            cartDto.setCartOwnerEmail(email);
            rabbitTemplate.convertAndSend(exchange, orderRoutingKey, cartDto);
            return new ResponseEntity<>("Product with id " + id + " added to cart successfully", HttpStatus.OK);
        } else {
            throw new ProductNotFoundException("Product with id " + id + " is not found.");
        }
    }
}
