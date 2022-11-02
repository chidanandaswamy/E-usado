package com.stackroute.slotservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.slotservice.exception.SlotNotFoundException;
import com.stackroute.slotservice.model.CreateSlot;
import com.stackroute.slotservice.model.DateTimeSlots;
import com.stackroute.slotservice.model.DbSequence;
import com.stackroute.slotservice.model.SlotStatus;
import com.stackroute.slotservice.repository.CreateSlotRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CreateSlotServiceImp.class})
@ExtendWith(SpringExtension.class)
class CreateSlotServiceImpTest {
    @MockBean
    private CreateSlotRepository createSlotRepository;

    @Autowired
    private CreateSlotServiceImp createSlotServiceImp;

    @MockBean
    private MongoOperations mongoOperations;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    /**
     * Method under test: {@link CreateSlotServiceImp#getSequenceNumber(String)}
     */
    @Test
    void testGetSequenceNumber() {
        DbSequence dbSequence = new DbSequence();
        dbSequence.setId("42");
        dbSequence.setSeq(1);
        when(mongoOperations.findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DbSequence>) any())).thenReturn(dbSequence);
        assertEquals(1, createSlotServiceImp.getSequenceNumber("Sequence Name"));
        verify(mongoOperations).findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DbSequence>) any());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#getSequenceNumber(String)}
     */
    @Test
    void testGetSequenceNumber2() {
        DbSequence dbSequence = mock(DbSequence.class);
        when(dbSequence.getSeq()).thenReturn(1);
        doNothing().when(dbSequence).setId((String) any());
        doNothing().when(dbSequence).setSeq(anyInt());
        dbSequence.setId("42");
        dbSequence.setSeq(1);
        when(mongoOperations.findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DbSequence>) any())).thenReturn(dbSequence);
        assertEquals(1, createSlotServiceImp.getSequenceNumber("Sequence Name"));
        verify(mongoOperations).findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DbSequence>) any());
        verify(dbSequence).getSeq();
        verify(dbSequence).setId((String) any());
        verify(dbSequence).setSeq(anyInt());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#findAllUsers()}
     */
    @Test
    void testFindAllUsers() {
        when(createSlotRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(createSlotServiceImp.findAllUsers().isEmpty());
        verify(createSlotRepository).findAll();
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#findAllUsers()}
     */
    @Test
    void testFindAllUsers2() {
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

        ArrayList<CreateSlot> createSlotList = new ArrayList<>();
        createSlotList.add(createSlot);
        when(createSlotRepository.findAll()).thenReturn(createSlotList);
        assertEquals(1, createSlotServiceImp.findAllUsers().size());
        verify(createSlotRepository).findAll();
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#findAllUsers()}
     */
    @Test
    void testFindAllUsers3() {
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

        ArrayList<CreateSlot> createSlotList = new ArrayList<>();
        createSlotList.add(createSlot1);
        createSlotList.add(createSlot);
        when(createSlotRepository.findAll()).thenReturn(createSlotList);
        assertEquals(1, createSlotServiceImp.findAllUsers().size());
        verify(createSlotRepository).findAll();
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#findAllUsers()}
     */
    @Test
    void testFindAllUsers4() {
        when(createSlotRepository.findAll()).thenThrow(new SlotNotFoundException("An error occurred"));
        assertThrows(SlotNotFoundException.class, () -> createSlotServiceImp.findAllUsers());
        verify(createSlotRepository).findAll();
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#getById(long)}
     */
    @Test
    void testGetById() {
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
        Optional<CreateSlot> ofResult = Optional.of(createSlot);
        when(createSlotRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(createSlot, createSlotServiceImp.getById(123L));
        verify(createSlotRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#getById(long)}
     */
    @Test
    void testGetById2() {
        when(createSlotRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(SlotNotFoundException.class, () -> createSlotServiceImp.getById(123L));
        verify(createSlotRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#getById(long)}
     */
    @Test
    void testGetById3() {
        when(createSlotRepository.findById((Long) any())).thenThrow(new SlotNotFoundException("An error occurred"));
        assertThrows(SlotNotFoundException.class, () -> createSlotServiceImp.getById(123L));
        verify(createSlotRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#addCreateSlot(CreateSlot)}
     */
    @Test
    void testAddCreateSlot() throws AmqpException {
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
        when(createSlotRepository.save((CreateSlot) any())).thenReturn(createSlot);
        doNothing().when(rabbitTemplate).convertAndSend((String) any(), (String) any(), (Object) any());

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
        ResponseEntity<String> actualAddCreateSlotResult = createSlotServiceImp.addCreateSlot(createSlot1);
        assertEquals("slot added successfully.", actualAddCreateSlotResult.getBody());
        assertEquals(HttpStatus.CREATED, actualAddCreateSlotResult.getStatusCode());
        assertTrue(actualAddCreateSlotResult.getHeaders().isEmpty());
        verify(createSlotRepository).save((CreateSlot) any());
        verify(rabbitTemplate).convertAndSend((String) any(), (String) any(), (Object) any());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#addCreateSlot(CreateSlot)}
     */
    @Test
    void testAddCreateSlot2() throws AmqpException {
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
        when(createSlotRepository.save((CreateSlot) any())).thenReturn(createSlot);
        doThrow(new SlotNotFoundException("An error occurred")).when(rabbitTemplate)
                .convertAndSend((String) any(), (String) any(), (Object) any());

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
        assertThrows(SlotNotFoundException.class, () -> createSlotServiceImp.addCreateSlot(createSlot1));
        verify(createSlotRepository).save((CreateSlot) any());
        verify(rabbitTemplate).convertAndSend((String) any(), (String) any(), (Object) any());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#UpdateSlotById(CreateSlot, long)}
     */
    @Test
    void testUpdateSlotById() {
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
        Optional<CreateSlot> ofResult = Optional.of(createSlot);

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
        when(createSlotRepository.save((CreateSlot) any())).thenReturn(createSlot1);
        when(createSlotRepository.findById((Long) any())).thenReturn(ofResult);

        CreateSlot createSlot2 = new CreateSlot();
        createSlot2.setBuyerEmailId("42");
        createSlot2.setBuyerName("Buyer Name");
        createSlot2.setDate("2020-03-01");
        createSlot2.setDateTimeSlots(new ArrayList<>());
        createSlot2.setDescription("The characteristics of someone or something");
        createSlot2.setSellerEmailId("42");
        createSlot2.setSellerName("Seller Name");
        createSlot2.setSlotId(123L);
        createSlot2.setStatus(SlotStatus.PROGRESS);
        assertSame(createSlot1, createSlotServiceImp.UpdateSlotById(createSlot2, 123L));
        verify(createSlotRepository).save((CreateSlot) any());
        verify(createSlotRepository).findById((Long) any());
        assertEquals(123L, createSlot2.getSlotId());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#UpdateSlotById(CreateSlot, long)}
     */
    @Test
    void testUpdateSlotById2() {
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
        Optional<CreateSlot> ofResult = Optional.of(createSlot);
        when(createSlotRepository.save((CreateSlot) any())).thenThrow(new SlotNotFoundException("An error occurred"));
        when(createSlotRepository.findById((Long) any())).thenReturn(ofResult);

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
        assertThrows(SlotNotFoundException.class, () -> createSlotServiceImp.UpdateSlotById(createSlot1, 123L));
        verify(createSlotRepository).save((CreateSlot) any());
        verify(createSlotRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#UpdateSlotById(CreateSlot, long)}
     */
    @Test
    void testUpdateSlotById3() {
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
        when(createSlotRepository.save((CreateSlot) any())).thenReturn(createSlot);
        when(createSlotRepository.findById((Long) any())).thenReturn(null);

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
        assertThrows(SlotNotFoundException.class, () -> createSlotServiceImp.UpdateSlotById(createSlot1, 123L));
        verify(createSlotRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#UpdateSlotById(CreateSlot, long)}
     */
    @Test
    void testUpdateSlotById4() {
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
        when(createSlotRepository.save((CreateSlot) any())).thenReturn(createSlot);
        when(createSlotRepository.findById((Long) any())).thenReturn(Optional.empty());

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
        assertThrows(SlotNotFoundException.class, () -> createSlotServiceImp.UpdateSlotById(createSlot1, 123L));
        verify(createSlotRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#UpdateSlotById(CreateSlot, long)}
     */
    @Test
    void testUpdateSlotById5() {
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
        Optional<CreateSlot> ofResult = Optional.of(createSlot);

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
        when(createSlotRepository.save((CreateSlot) any())).thenReturn(createSlot1);
        when(createSlotRepository.findById((Long) any())).thenReturn(ofResult);
        CreateSlot createSlot2 = mock(CreateSlot.class);
        doNothing().when(createSlot2).setBuyerEmailId((String) any());
        doNothing().when(createSlot2).setBuyerName((String) any());
        doNothing().when(createSlot2).setDate((String) any());
        doNothing().when(createSlot2).setDateTimeSlots((List<DateTimeSlots>) any());
        doNothing().when(createSlot2).setDescription((String) any());
        doNothing().when(createSlot2).setSellerEmailId((String) any());
        doNothing().when(createSlot2).setSellerName((String) any());
        doNothing().when(createSlot2).setSlotId(anyLong());
        doNothing().when(createSlot2).setStatus((SlotStatus) any());
        createSlot2.setBuyerEmailId("42");
        createSlot2.setBuyerName("Buyer Name");
        createSlot2.setDate("2020-03-01");
        createSlot2.setDateTimeSlots(new ArrayList<>());
        createSlot2.setDescription("The characteristics of someone or something");
        createSlot2.setSellerEmailId("42");
        createSlot2.setSellerName("Seller Name");
        createSlot2.setSlotId(123L);
        createSlot2.setStatus(SlotStatus.PROGRESS);
        assertSame(createSlot1, createSlotServiceImp.UpdateSlotById(createSlot2, 123L));
        verify(createSlotRepository).save((CreateSlot) any());
        verify(createSlotRepository).findById((Long) any());
        verify(createSlot2).setBuyerEmailId((String) any());
        verify(createSlot2).setBuyerName((String) any());
        verify(createSlot2).setDate((String) any());
        verify(createSlot2).setDateTimeSlots((List<DateTimeSlots>) any());
        verify(createSlot2).setDescription((String) any());
        verify(createSlot2).setSellerEmailId((String) any());
        verify(createSlot2).setSellerName((String) any());
        verify(createSlot2, atLeast(1)).setSlotId(anyLong());
        verify(createSlot2).setStatus((SlotStatus) any());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#deleteCreatedSlotById(long)}
     */
    @Test
    void testDeleteCreatedSlotById() {
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
        Optional<CreateSlot> ofResult = Optional.of(createSlot);
        doNothing().when(createSlotRepository).deleteById((Long) any());
        when(createSlotRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseEntity<String> actualDeleteCreatedSlotByIdResult = createSlotServiceImp.deleteCreatedSlotById(123L);
        assertEquals("Slot with id 123 deleted successfull", actualDeleteCreatedSlotByIdResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteCreatedSlotByIdResult.getStatusCode());
        assertTrue(actualDeleteCreatedSlotByIdResult.getHeaders().isEmpty());
        verify(createSlotRepository).findById((Long) any());
        verify(createSlotRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#deleteCreatedSlotById(long)}
     */
    @Test
    void testDeleteCreatedSlotById2() {
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
        Optional<CreateSlot> ofResult = Optional.of(createSlot);
        doThrow(new SlotNotFoundException("An error occurred")).when(createSlotRepository).deleteById((Long) any());
        when(createSlotRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(SlotNotFoundException.class, () -> createSlotServiceImp.deleteCreatedSlotById(123L));
        verify(createSlotRepository).findById((Long) any());
        verify(createSlotRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#deleteCreatedSlotById(long)}
     */
    @Test
    void testDeleteCreatedSlotById3() {
        doNothing().when(createSlotRepository).deleteById((Long) any());
        when(createSlotRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(SlotNotFoundException.class, () -> createSlotServiceImp.deleteCreatedSlotById(123L));
        verify(createSlotRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#deleteAllCreateSlots()}
     */
    @Test
    void testDeleteAllCreateSlots() {
        doNothing().when(createSlotRepository).deleteAll();
        createSlotServiceImp.deleteAllCreateSlots();
        verify(createSlotRepository).deleteAll();
    }

    /**
     * Method under test: {@link CreateSlotServiceImp#deleteAllCreateSlots()}
     */
    @Test
    void testDeleteAllCreateSlots2() {
        doThrow(new SlotNotFoundException("An error occurred")).when(createSlotRepository).deleteAll();
        assertThrows(SlotNotFoundException.class, () -> createSlotServiceImp.deleteAllCreateSlots());
        verify(createSlotRepository).deleteAll();
    }
}

