package com.stackroute.slotservice.service;

import com.stackroute.slotservice.model.CreateSlot;
import org.springframework.http.ResponseEntity;


public interface CreateSlotService {




    ResponseEntity<?> getAllSlots();

   // ResponseEntity<CreateSlot> getSlotById(Long id);

    ResponseEntity<CreateSlot> getSlotById(Long id);

    ResponseEntity<String> addCreateSlot(CreateSlot createSlot);

    void deleteAllCreateSlots();
    ResponseEntity<String> deleteCreatedSlotById(Long id);
}
