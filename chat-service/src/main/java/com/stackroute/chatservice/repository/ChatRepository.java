package com.stackroute.chatservice.repository;

import com.stackroute.chatservice.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRepository extends MongoRepository<Chat, Long> {


    //Optional<Chat> findById(UUID productId);
}
