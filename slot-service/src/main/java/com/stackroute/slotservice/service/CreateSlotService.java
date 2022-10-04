package com.stackroute.slotservice.service;

import com.stackroute.slotservice.model.CreateSlot;
import org.springframework.stereotype.Service;

import java.util.HashSet;


public interface CreateSlotService {


    public HashSet<CreateSlot> getAllSlots();
    public CreateSlot getSlotById(String id);
    public void addCreateSlot(CreateSlot createSlot);
    public void deleteAllCreateSlots();
}
