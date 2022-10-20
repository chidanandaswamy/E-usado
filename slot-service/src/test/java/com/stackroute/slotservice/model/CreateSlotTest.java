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

        //dateTimeSlots=new ArrayList<>();

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



//package com.stackroute.keepnote.test.model;
//
//        import java.util.Date;
//        import org.junit.Before;
//        import org.junit.Test;
//        import org.meanbean.test.BeanTester;
//
//        import com.stackroute.keepnote.model.User;
//
//public class UserTest {
//
//    private User user;
//
//    @Before
//    public void setUp() throws Exception {
//
//        user = new User();
//        user.setUserId("Jhon123");
//        user.setUserName("Jhon Simon");
//        user.setUserPassword("123456");
//        user.setUserMobile("9898989898");
//        user.setUserAddedDate(new Date());
//
//    }
//
//    @Test
//    public void test() {
//        new BeanTester().testBean(User.class);
//    }
//
//}
