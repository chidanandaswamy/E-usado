package com.stackroute.chatservice.repository;

import com.stackroute.chatservice.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ChatRepository extends MongoRepository<Chat, Long> {
    @Query(value="{productId:'?0'}")
    List <Chat> findByProductId(String productId);

   // @Query("{'productId':?1}")
    //Optional<Chat> findById(UUID productId);

}
