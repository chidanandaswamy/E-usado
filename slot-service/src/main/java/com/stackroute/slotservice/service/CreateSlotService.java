package com.stackroute.slotservice.service;

import com.stackroute.slotservice.model.CreateSlot;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;



public interface CreateSlotService {




   // ResponseEntity<?> getAllSlots();
    HashSet<CreateSlot> findAllUsers();

   // ResponseEntity<CreateSlot> getSlotById(Long id);

//    ResponseEntity<CreateSlot> getSlotById(long slotId);
 CreateSlot getById(long slotId);

    ResponseEntity<String> addCreateSlot(CreateSlot createSlot);

//    ResponseEntity<String> updateSlotById(long slotId, CreateSlot createSlot);
 CreateSlot UpdateSlotById(CreateSlot createSlot, long slotId);

    void deleteAllCreateSlots();
    ResponseEntity<String> deleteCreatedSlotById(long slotId);
}
