package com.stackroute.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "product_image")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {
    @Id
    private UUID productImageId;
    private Binary productImage;
}
