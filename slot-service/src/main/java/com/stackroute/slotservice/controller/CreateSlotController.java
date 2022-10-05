package com.stackroute.slotservice.controller;


import com.stackroute.slotservice.model.CreateSlot;
import com.stackroute.slotservice.service.CreateSlotServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;


@RestController
@RequestMapping("api/v1")
public class CreateSlotController {
    @Autowired
    CreateSlotServiceImp createSlotServiceImp;

    @PostMapping("/add")
    public void addCreateSlot(@RequestBody CreateSlot createSlot){
        createSlotServiceImp.addCreateSlot(createSlot);
    }
    @GetMapping("getAllSlots")
    public HashSet<CreateSlot> getCreateSlot(){
        return createSlotServiceImp.getAllSlots();
    }
    @GetMapping("/getSlot/{id}")
    public CreateSlot getCreateSlotById(@PathVariable String id){
        return createSlotServiceImp.getSlotById(id);
    }
    @DeleteMapping("/deleteAll")
    public void deleteCreateSlots(){
        createSlotServiceImp.deleteAllCreateSlots();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCreatedSlotById(@PathVariable String id){
        return new ResponseEntity<Boolean>(Boolean.valueOf(createSlotServiceImp.deleteCreatedSlotById(id)), HttpStatus.CREATED);
    }


}
