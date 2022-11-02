package com.stackroute.slotservice.controller;
import com.stackroute.slotservice.model.CreateSlot;
import com.stackroute.slotservice.model.DateTimeSlots;
import com.stackroute.slotservice.model.SellerSlots;
import com.stackroute.slotservice.model.SlotStatus;
import com.stackroute.slotservice.service.CreateSlotService;
import com.stackroute.slotservice.service.CreateSlotServiceImp;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.data.repository.init.ResourceReader.Type.JSON;


@RunWith(SpringRunner.class)
@WebMvcTest
public class CreateSlotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateSlot createSlot;

    @MockBean
    private DateTimeSlots dateTimeSlots;

    @MockBean
    private SellerSlots sellerSlots;

    @MockBean
    private List<SellerSlots> sellerSlotsList;

    @MockBean
    private List<DateTimeSlots> dateTimeSlotsList;

    @MockBean
    private CreateSlotServiceImp createSlotServiceImp;

    @InjectMocks
    private CreateSlotController createSlotController;
    private List<CreateSlot> createSlots;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(createSlotController).build();



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



        createSlots = new ArrayList<>();
        createSlots.add(createSlot);


    }




    }












