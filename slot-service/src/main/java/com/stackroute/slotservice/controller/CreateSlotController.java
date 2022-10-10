package com.stackroute.slotservice.controller;
import com.stackroute.slotservice.model.CreateSlot;
import com.stackroute.slotservice.service.CreateSlotServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("api/v1")
public class CreateSlotController {
    @Autowired
    CreateSlotServiceImp createSlotServiceImp;

    @RequestMapping(value = "/add", method= RequestMethod.POST)
    public ResponseEntity<?> addCreateSlot(@RequestBody CreateSlot createSlot){
        createSlot.setSlotId(createSlotServiceImp.getSequenceNumber(createSlot.SEQUENCE_NAME));
        return createSlotServiceImp.addCreateSlot(createSlot);
    }

    @RequestMapping(value = "/getAllSlot", method= RequestMethod.GET)
    public ResponseEntity<?> getCreatedSlot(){
        return createSlotServiceImp.getAllSlots();
    }


    @RequestMapping(value = "/getSlotById/{id}", method= RequestMethod.GET)
    public ResponseEntity<?> getCreateSlotById(@PathVariable Long id){
        return createSlotServiceImp.getSlotById(id);
    }


    @DeleteMapping("/deleteAll")
    public void deleteCreateSlots(){
        createSlotServiceImp.deleteAllCreateSlots();
    }


    @RequestMapping(value = "/deleteById/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> deleteCreatedSlotById(@PathVariable Long id){
                 return createSlotServiceImp.deleteCreatedSlotById(id);
}



}
