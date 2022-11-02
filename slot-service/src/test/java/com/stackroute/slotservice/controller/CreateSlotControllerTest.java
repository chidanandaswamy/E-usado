package com.stackroute.slotservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.slotservice.exception.SlotNotFoundException;
import com.stackroute.slotservice.model.CreateSlot;
import com.stackroute.slotservice.model.SlotStatus;
import com.stackroute.slotservice.service.CreateSlotServiceImp;

import java.util.ArrayList;
import java.util.HashSet;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CreateSlotController.class})
@ExtendWith(SpringExtension.class)
class CreateSlotControllerTest {
    @Autowired
    private CreateSlotController createSlotController;

    @MockBean
    private CreateSlotServiceImp createSlotServiceImp;

    /**
     * Method under test: {@link CreateSlotController#addCreateSlot(CreateSlot)}
     */
    @Test
    void testAddCreateSlot() throws Exception {
        when(createSlotServiceImp.getSequenceNumber((String) any())).thenReturn(10);
        when(createSlotServiceImp.addCreateSlot((CreateSlot) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        CreateSlot createSlot = new CreateSlot();
        createSlot.setBuyerEmailId("42");
        createSlot.setBuyerName("Buyer Name");
        createSlot.setDate("2020-03-01");
        createSlot.setDateTimeSlots(new ArrayList<>());
        createSlot.setDescription("The characteristics of someone or something");
        createSlot.setSellerEmailId("42");
        createSlot.setSellerName("Seller Name");
        createSlot.setSlotId(123L);
        createSlot.setStatus(SlotStatus.PROGRESS);
        String content = (new ObjectMapper()).writeValueAsString(createSlot);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/BookSlot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(createSlotController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link CreateSlotController#getById(long, HttpSession)}
     */
    @Test
    void testGetById() throws Exception {
        CreateSlot createSlot = new CreateSlot();
        createSlot.setBuyerEmailId("42");
        createSlot.setBuyerName("Buyer Name");
        createSlot.setDate("2020-03-01");
        createSlot.setDateTimeSlots(new ArrayList<>());
        createSlot.setDescription("The characteristics of someone or something");
        createSlot.setSellerEmailId("42");
        createSlot.setSellerName("Seller Name");
        createSlot.setSlotId(123L);
        createSlot.setStatus(SlotStatus.PROGRESS);
        when(createSlotServiceImp.getById(anyLong())).thenReturn(createSlot);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/BookSlot/{slotId}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(createSlotController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"slotId\":123,\"sellerName\":\"Seller Name\",\"sellerEmailId\":\"42\",\"buyerName\":\"Buyer Name\",\"buyerEmailId"
                                        + "\":\"42\",\"status\":\"PROGRESS\",\"dateTimeSlots\":[],\"date\":\"2020-03-01\",\"description\":\"The characteristics"
                                        + " of someone or something\"}"));
    }

    /**
     * Method under test: {@link CreateSlotController#getById(long, HttpSession)}
     */
    @Test
    void testGetById2() throws Exception {
        when(createSlotServiceImp.getById(anyLong())).thenThrow(new SlotNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/BookSlot/{slotId}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(createSlotController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Slot Not Found"));
    }

    /**
     * Method under test: {@link CreateSlotController#deleteCreateSlots()}
     */
    @Test
    void testDeleteCreateSlots() throws Exception {
        doNothing().when(createSlotServiceImp).deleteAllCreateSlots();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/BookSlot");
        MockMvcBuilders.standaloneSetup(createSlotController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CreateSlotController#findAll()}
     */
    @Test
    void testFindAll() throws Exception {
        when(createSlotServiceImp.findAllUsers()).thenReturn(new HashSet<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/BookSlot");
        MockMvcBuilders.standaloneSetup(createSlotController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CreateSlotController#UpdateSlotById(CreateSlot, long)}
     */
    @Test
    void testUpdateSlotById() throws Exception {
        CreateSlot createSlot = new CreateSlot();
        createSlot.setBuyerEmailId("42");
        createSlot.setBuyerName("Buyer Name");
        createSlot.setDate("2020-03-01");
        createSlot.setDateTimeSlots(new ArrayList<>());
        createSlot.setDescription("The characteristics of someone or something");
        createSlot.setSellerEmailId("42");
        createSlot.setSellerName("Seller Name");
        createSlot.setSlotId(123L);
        createSlot.setStatus(SlotStatus.PROGRESS);
        when(createSlotServiceImp.UpdateSlotById((CreateSlot) any(), anyLong())).thenReturn(createSlot);

        CreateSlot createSlot1 = new CreateSlot();
        createSlot1.setBuyerEmailId("42");
        createSlot1.setBuyerName("Buyer Name");
        createSlot1.setDate("2020-03-01");
        createSlot1.setDateTimeSlots(new ArrayList<>());
        createSlot1.setDescription("The characteristics of someone or something");
        createSlot1.setSellerEmailId("42");
        createSlot1.setSellerName("Seller Name");
        createSlot1.setSlotId(123L);
        createSlot1.setStatus(SlotStatus.PROGRESS);
        String content = (new ObjectMapper()).writeValueAsString(createSlot1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/BookSlot/{slotId}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(createSlotController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"slotId\":123,\"sellerName\":\"Seller Name\",\"sellerEmailId\":\"42\",\"buyerName\":\"Buyer Name\",\"buyerEmailId"
                                        + "\":\"42\",\"status\":\"PROGRESS\",\"dateTimeSlots\":[],\"date\":\"2020-03-01\",\"description\":\"The characteristics"
                                        + " of someone or something\"}"));
    }

    /**
     * Method under test: {@link CreateSlotController#deleteCreatedSlotById(long)}
     */
    @Test
    void testDeleteCreatedSlotById() throws Exception {
        when(createSlotServiceImp.deleteCreatedSlotById(anyLong())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/BookSlot/{slotId}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(createSlotController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
}

