package com.stackroute.slotservice.controller;
import com.stackroute.slotservice.model.CreateSlot;
import com.stackroute.slotservice.service.CreateSlotService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.mock.mockito.MockBean;


public class CreateSlotControllerTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("before class");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("before");
    }



    @MockBean
    private CreateSlotService createSlotService;


    private CreateSlot createSlot;





//************************************************************************

    /*
    @Autowired
    private CreateSlotController createSlotController;

    @MockBean
    private CreateSlotService createSlotService;


    private CreateSlot createSlot;


    private DateTimeSlots dateTimeSlots;

    private SellerSlots sellerSlots;



    @Test
    void testGetBySlotIdSuccess() throws Exception {

      CreateSlot createSlot1 = new CreateSlot();

      SellerSlots sellerSlots =new SellerSlots();

      List<SellerSlots> sellerSlotLists = new ArrayList<>();

      List<DateTimeSlots> dateTimeSlotsList = new ArrayList<>();

      DateTimeSlots dateTimeSlots =new DateTimeSlots();

        createSlot1.setSellerName("bittu");
        createSlot1.setSellerEmailId("bittu@gmail.com");
        createSlot1.setBuyerName("Raju");
        createSlot1.setBuyerEmailId("rk@gmail.com");
        createSlot1.getStatus();
        createSlot1.setDateTimeSlots(dateTimeSlotsList);
        createSlot1.setDate("12/12/22");
        createSlot1.setDescription("created slot");   */


// ********************************************************************************

//        createSlot1.
//                setDateTimeSlots(dateTimeSlots.setSlotDate("12/05/22"),dateTimeSlotsList.
//                     setSellerSlots(sellerSlotLists.setSlotId("1"), sellerSlotLists.setStartTime("12:00"), sellerSlotLists.setEndTime("1:00"))
//             );



//        List<DateTimeSlots> dateTimeSlotsList = new ArrayList<>();
//        DateTimeSlots dateTimeSlots =new DateTimeSlots();

//        createSlot1.setSellerName("bittu");
//        createSlot1.setSellerEmailId("bittu@gmail.com");
//        createSlot1.setBuyerName("Raju");
//        createSlot1.setBuyerEmailId("rk@gmail.com");
//
//        dateTimeSlots.setSlotDate("12/05/22");










  //assertTrue( condition: true);


    @Test

    public void demoTestMethod(){
      System.out.println("hii");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("after class");
    }






}
