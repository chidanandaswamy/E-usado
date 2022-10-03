package com.stackroute.slotservice.service;

import com.stackroute.slotservice.model.CreateSlot;
import com.stackroute.slotservice.repository.CreateSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CreateSlotServiceImp implements CreateSlotService{

    HashSet<CreateSlot> CreatedSlotList=new HashSet<>();

    @Autowired
    CreateSlotRepository createSlotRepository;



    @Override
    public HashSet<CreateSlot> getAllSlots() {
        createSlotRepository.findAll().forEach(createSlot -> CreatedSlotList.add(createSlot));
        return CreatedSlotList;
    }

    @Override
    public CreateSlot getSlotById(String id) {
        return createSlotRepository.findById(id).get();
    }

    @Override
    public void addCreateSlot(CreateSlot createSlot) {
        createSlotRepository.save(createSlot);

    }

    @Override
    public void deleteAllCreateSlots() {
        createSlotRepository.deleteAll();

    }
}
