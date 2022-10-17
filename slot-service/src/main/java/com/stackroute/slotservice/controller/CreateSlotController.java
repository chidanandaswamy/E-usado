package com.stackroute.slotservice.controller;
import com.stackroute.slotservice.exception.SlotNotFoundException;
import com.stackroute.slotservice.model.CreateSlot;
import com.stackroute.slotservice.service.CreateSlotServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;


@RestController
@RequestMapping("api/v1")
public class CreateSlotController {
    ResponseEntity responseEntity;
    @Autowired

    CreateSlotServiceImp createSlotServiceImp;

   // @RequestMapping(value = "/add", method= RequestMethod.POST)
    @PostMapping("/add")
    public ResponseEntity<?> addCreateSlot(@RequestBody CreateSlot createSlot){
        createSlot.setSlotId(createSlotServiceImp.getSequenceNumber(createSlot.SEQUENCE_NAME));
        return createSlotServiceImp.addCreateSlot(createSlot);
    }

//    @RequestMapping(value = "/getAllSlot", method= RequestMethod.GET)
//    public ResponseEntity<?> getCreatedSlot(){
//        return createSlotServiceImp.getAllSlots();
//    }

   // @RequestMapping(value="/users" ,method=RequestMethod.GET)
    @GetMapping("/users")
    public HashSet<CreateSlot> findAll() {
        return createSlotServiceImp.findAllUsers();
    }


//    @RequestMapping(value = "/getSlotById/{id}", method= RequestMethod.GET)
//    public ResponseEntity<?> getCreateSlotById(@PathVariable long slotId){
//        return createSlotServiceImp.getSlotById(slotId);
//    }


   // @RequestMapping(value = "/getSlotById/{slotId}", method= RequestMethod.GET)
    @GetMapping("/getSlotById/{slotId}")
    public ResponseEntity<?> getById(@PathVariable long slotId, HttpSession session) {
        try {
            return new ResponseEntity<CreateSlot>(createSlotServiceImp.getById(slotId), HttpStatus.CREATED);

        } catch (SlotNotFoundException userNotFound) {
            return new ResponseEntity<String>("Slot Not Found", HttpStatus.NOT_FOUND);
        }
    }


//    @RequestMapping(value = "/updateSlot/{slotId}", method= RequestMethod.PUT)
//    public ResponseEntity<?> updateSlotById(@PathVariable long slotId, @RequestBody CreateSlot createSlot){
//        return createSlotServiceImp.updateSlotById(slotId, createSlot);
//    }



    //@RequestMapping(value="/users/update/{slotId}",method=RequestMethod.PUT)
    @PutMapping("/users/update/{slotId}")
    public ResponseEntity<CreateSlot> UpdateSlotById( @RequestBody CreateSlot createSlot,@PathVariable long slotId){
        responseEntity=new ResponseEntity<CreateSlot>(createSlotServiceImp.UpdateSlotById(createSlot,slotId),HttpStatus.OK);
        return responseEntity;
    }



    @DeleteMapping("/deleteAll")
    public void deleteCreateSlots(){
        createSlotServiceImp.deleteAllCreateSlots();
    }


    //@RequestMapping(value = "/deleteById/{slotId}", method= RequestMethod.DELETE)
    @DeleteMapping("/deleteById/{slotId}")
    public ResponseEntity<?> deleteCreatedSlotById(@PathVariable long slotId){
                 return createSlotServiceImp.deleteCreatedSlotById(slotId);
}



}
