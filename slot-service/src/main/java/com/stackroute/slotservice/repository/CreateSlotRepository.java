package com.stackroute.slotservice.repository;

import com.stackroute.slotservice.model.CreateSlot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateSlotRepository extends MongoRepository<CreateSlot,Long> {

}
