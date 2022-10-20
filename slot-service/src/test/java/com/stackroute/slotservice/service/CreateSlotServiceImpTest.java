package com.stackroute.slotservice.service;

import com.stackroute.slotservice.model.CreateSlot;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class CreateSlotServiceImpTest {

    @MockBean
    private CreateSlot createSlot;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);


       // location = new Location("mangalore", new Double[]{-88.00, -90.90});

        createSlot = new CreateSlot(

        );





    }



}
