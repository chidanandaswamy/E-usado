package com.stackroute.slotservice.model;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;

import java.util.*;


public class CreateSlotTest {

    private CreateSlot createSlot;
    private DateTimeSlots dateTimeSlots;

    @Before
    public  void setUp() throws Exception {
        createSlot = new CreateSlot();

        createSlot.setSellerName("bittu");
        createSlot.setSellerEmailId("bittu@gmail.com");
        createSlot.setBuyerName("Raju");
        createSlot.setBuyerEmailId("rk@gmail.com");
        createSlot.getStatus();
        createSlot.setDateTimeSlots(new ArrayList<>());
        createSlot.setDate("12/12/22");
        createSlot.setDescription("created slot");

    }

    @Test
    public void test() {
        new BeanTester().testBean(CreateSlot.class);
    }

}



