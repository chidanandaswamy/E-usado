package com.stackroute.slotservice.service;

import com.stackroute.slotservice.model.CreateSlot;


import java.util.HashSet;


public interface CreateSlotService {


    HashSet<CreateSlot> getAllSlots();
   CreateSlot getSlotById(String id);
     void addCreateSlot(CreateSlot createSlot);
    void deleteAllCreateSlots();
     Boolean deleteCreatedSlotById(String id);
}
