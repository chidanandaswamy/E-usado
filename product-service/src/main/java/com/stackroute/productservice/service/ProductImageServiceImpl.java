package com.stackroute.productservice.service;

import com.fasterxml.uuid.Generators;
import com.stackroute.productservice.model.ProductImage;
import com.stackroute.productservice.repository.ProductImageRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ProductImageServiceImpl implements ProductImageService{

    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public ResponseEntity<?> addProductImage(MultipartFile file) {
        ProductImage productImage = new ProductImage();
        productImage.setProductImageId(Generators.timeBasedGenerator().generate());
        try {
            productImage.setProductImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ProductImage createdProductImage = productImageRepository.save(productImage);

        if(createdProductImage != null && createdProductImage.getProductImageId() != null){
            return new ResponseEntity<UUID>(createdProductImage.getProductImageId(), HttpStatus.CREATED);
        } else {
            return null;
        }
    }
}
