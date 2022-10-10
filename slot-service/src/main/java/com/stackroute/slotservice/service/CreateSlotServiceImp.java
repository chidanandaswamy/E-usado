package com.stackroute.slotservice.service;
import com.stackroute.slotservice.exception.SlotNotFoundException;
import com.stackroute.slotservice.model.CreateSlot;
import com.stackroute.slotservice.model.DbSequence;
import com.stackroute.slotservice.repository.CreateSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;


@Service
public class CreateSlotServiceImp implements CreateSlotService{

    HashSet<CreateSlot> CreatedSlotList=new HashSet<>();

    @Autowired
    CreateSlotRepository createSlotRepository;


    @Autowired
    private MongoOperations mongoOperations;

    public int getSequenceNumber(String sequenceName)
    {
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seq", 1);
        DbSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true), DbSequence.class);
        return !Objects.isNull(counter)?counter.getSeq() : 1;
    }



    @Override
    public ResponseEntity<?> getAllSlots() {
        List<CreateSlot> createslot = createSlotRepository.findAll();
        if(createslot != null && createslot.size() > 0){
            return new ResponseEntity<List<CreateSlot>>(createslot, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No slots found", HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<CreateSlot> getSlotById(Long id) {
        Optional<CreateSlot> createSlot = createSlotRepository.findById(id);
        if(createSlot.isPresent()){
            return new ResponseEntity<>(createSlot.get(), HttpStatus.OK);
        } else {
            throw new SlotNotFoundException("slot with id " + id + " is not found.");
        }
    }



    @Override
    public ResponseEntity<String> addCreateSlot(CreateSlot createSlot) {
        CreateSlot savedSlot = createSlotRepository.save(createSlot);
        if(savedSlot != null ){
            return new ResponseEntity<>("slot added successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Could not create slot.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Override
    public ResponseEntity<String> updateSlotById(Long id, CreateSlot createSlot) {
        Optional<CreateSlot> slotOptional = createSlotRepository.findById(id);
        if(slotOptional.isPresent()){
            CreateSlot savedSlot = createSlotRepository.save(createSlot);
            if(savedSlot != null){
                return new ResponseEntity<>("Product with id " + id + " not found", HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Update of product with id " + id + " failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new SlotNotFoundException("Product with id " + id + " is not found.");
        }
    }





    @Override
    public ResponseEntity<String> deleteCreatedSlotById(Long id) {
        Optional<CreateSlot> createSlot = createSlotRepository.findById(id);
        System.out.println(createSlot);
        if(createSlot.isPresent()){
            createSlotRepository.deleteById(id);
            return new ResponseEntity<>("Slot with id " + id + " deleted successfull", HttpStatus.OK);
        } else {
            throw new SlotNotFoundException("Slot with id " + id + " is not found.");
        }
    }


    @Override
    public void deleteAllCreateSlots() {
        createSlotRepository.deleteAll();

    }
}
