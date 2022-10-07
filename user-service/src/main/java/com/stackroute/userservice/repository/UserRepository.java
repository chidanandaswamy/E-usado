package com.stackroute.userservice.repository;
import com.stackroute.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {


    @Query("{'email' : ?0}")
    User findByEmail(String email);



}

