package com.stackroute.slotservice.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.meanbean.test.BeanTester;

import java.util.*;


public class CreateSlotTest {

    private CreateSlot createSlot;
    private DateTimeSlots dateTimeSlots;
    private SellerSlots sellerSlots;
    private List<SellerSlots> sellerSlotsList;
    private List<DateTimeSlots> dateTimeSlotsList;

    @BeforeEach
    public  void setUp() throws Exception {

       sellerSlots =new SellerSlots();


        sellerSlots.setSlotId("1");
        sellerSlots.setStartTime("12:00");
        sellerSlots.setEndTime("1:00");

        //sellerSlotsList.add(sellerSlots);
        dateTimeSlots = new DateTimeSlots();
        dateTimeSlots.setSlotDate("12/08/22");

         dateTimeSlots.setSellerSlots(sellerSlotsList);
         dateTimeSlotsList.add(dateTimeSlots);

        createSlot = new CreateSlot();
        createSlot.setSellerName("bittu");
        createSlot.setSellerEmailId("bittu@gmail.com");
        createSlot.setBuyerName("Raju");
        createSlot.setBuyerEmailId("rk@gmail.com");
        createSlot.setStatus(SlotStatus.BOOKED);
        createSlot.setDateTimeSlots(dateTimeSlotsList);
        createSlot.setDate("12/12/22");
        createSlot.setDescription("created slot");



    }

    @Test
    public void test() {
        new BeanTester().testBean(CreateSlot.class);
    }

}



