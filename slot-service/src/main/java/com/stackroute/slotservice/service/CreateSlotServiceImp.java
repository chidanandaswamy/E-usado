package com.stackroute.slotservice.service;
import com.stackroute.slotservice.exception.SlotNotFoundException;
import com.stackroute.slotservice.model.CreateSlot;
import com.stackroute.slotservice.model.DbSequence;
import com.stackroute.slotservice.repository.CreateSlotRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

  //  HashSet<CreateSlot> CreatedSlotList=new HashSet<>();

    @Autowired
    CreateSlotRepository createSlotRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Autowired
    private MongoOperations mongoOperations;

    public int getSequenceNumber(String sequenceName)
    {
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seq", 1);
        DbSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true), DbSequence.class);
        return !Objects.isNull(counter)?counter.getSeq() : 1;
    }



//    @Override
//    public ResponseEntity<?> getAllSlots() {
//        List<CreateSlot> createslot = createSlotRepository.findAll();
//        if(createslot != null && createslot.size() > 0){
//            return new ResponseEntity<List<CreateSlot>>(createslot, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<String>("No slots found", HttpStatus.OK);
//        }
//    }

    @Override
    public HashSet<CreateSlot> findAllUsers() {
        HashSet<CreateSlot> userList=new HashSet();
//        createSlotRepository.findAll().forEach(user -> userList.add(user));
        createSlotRepository.findAll().forEach(c->userList.add(c));
        System.out.println(userList);
        return userList;
    }

//    @Override
//    public ResponseEntity<CreateSlot> getSlotById(long slotId) {
//        Optional<CreateSlot> createSlot = createSlotRepository.findById(slotId);
//        if(createSlot.isPresent()){
//            return new ResponseEntity<>(createSlot.get(), HttpStatus.OK);
//        } else {
//            throw new SlotNotFoundException("slot with id " + slotId + " is not found.");
//        }
//    }


    @Override
    public CreateSlot getById(long slotId) {
        Optional<CreateSlot> findById = createSlotRepository.findById(slotId);
        if (findById.isPresent()) {
            findById.get();
        } else {
            throw new SlotNotFoundException("User Not Found !!");
        }
        return findById.get();

    }



    @Override
    public ResponseEntity<String> addCreateSlot(CreateSlot createSlot) {



        CreateSlot savedSlot = createSlotRepository.save(createSlot);
        if(savedSlot != null ){

            rabbitTemplate.convertAndSend("Slot_exchange","Slot_routingkey",createSlot);
            return new ResponseEntity<>("slot added successfully.", HttpStatus.CREATED);
        }


        else {
            return new ResponseEntity<>("Could not create slot.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



//    @Override
//    public ResponseEntity<String> updateSlotById(long slotId, CreateSlot createSlot) {
//        Optional<CreateSlot> slotOptional = createSlotRepository.findById(slotId);
//        if(slotOptional.isPresent()){
//            CreateSlot savedSlot = createSlotRepository.save(createSlot);
//            if(savedSlot != null){
//                return new ResponseEntity<>("Product with id " + slotId + " not found", HttpStatus.ACCEPTED);
//            } else {
//                return new ResponseEntity<>("Update of product with id " + slotId + " failed", HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        } else {
//            throw new SlotNotFoundException("Product with id " + slotId + " is not found.");
//        }
//    }


    @Override
    public CreateSlot UpdateSlotById( CreateSlot createSlot, long slotId) {
        Optional<CreateSlot> slotOptional = createSlotRepository.findById(slotId);
        System.out.println(slotOptional);
        if (slotOptional != null && slotOptional.isPresent()==true) {
            createSlot.setSlotId(slotId);
            return createSlotRepository.save(createSlot);
        } else {
            throw new SlotNotFoundException("User with slotId " + slotId + " doesn't exist.");

        }
    }




    @Override
    public ResponseEntity<String> deleteCreatedSlotById(long slotId) {
        Optional<CreateSlot> createSlot = createSlotRepository.findById(slotId);
        System.out.println(createSlot);
        if(createSlot.isPresent()){
            createSlotRepository.deleteById(slotId);
            return new ResponseEntity<>("Slot with id " + slotId + " deleted successfull", HttpStatus.OK);
        } else {
            throw new SlotNotFoundException("Slot with id " + slotId + " is not found.");
        }
    }


    @Override
    public void deleteAllCreateSlots() {
        createSlotRepository.deleteAll();

    }
}
