//package com.stackroute.userservice.repository;
//
//import com.stackroute.userservice.exception.UserNotFoundException;
//import com.stackroute.userservice.model.Address;
//import com.stackroute.userservice.model.User;
//import com.stackroute.userservice.service.UserServiceImpl;
//import org.junit.Assert;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@ContextConfiguration(classes = {UserRepository.class})
//@ExtendWith(MockitoExtension.class)
//public class UserRepositoryTest {
//
////    @Autowired
////    private UserRepository userRepository;
////
////    private User user;
////    private Address address;
////
////    @BeforeEach
////    public void setUp() throws Exception {
////        user = new User();
////        address=new Address();
////        user.setName("Ashu");
////        user.setEmail("ashu@gmail.com");
////        user.setPassword("password123");
////        user.setContactNo(9876543210L);
////        user.setGender("male");
////
////        address.setHouseNumber(1);
////        address.setBuildingName("A");
////        address.setStreetName("Surya Mahal");
////        address.setLandmark("Near SR Mall");
////        address.setPinCode(764001);
////        address.setCity("Jeypore");
////        address.setState("Odisha");
//
////        userRepository.deleteByEmail("ashu@gmail.com");
//    }
//
////    @AfterEach
////    public void tearDown() throws Exception {
////        userRepository.deleteByEmail("ashu@gmail.com");
////
////    }
//
////    @Test
////    public void saveUserTest() {
////        userRepository.insert(user);
////        User fetchuser = userRepository.findByEmail("ashu@gmail.com");
////        System.out.println(fetchuser);
////        Assert.assertEquals(user.getEmail(), fetchuser.getEmail());
////    }
//
////    @Test
////    public void deleteByEmailTest() {
////        userRepository.insert(user);
////        User fetchuser = userRepository.findByEmail("ashu@gmail.com");
////        System.out.println(fetchuser);
////        Assert.assertEquals(user.getEmail(), fetchuser.getEmail());
////        userRepository.deleteByEmail(fetchuser.getEmail());
////        assertThrows(UserNotFoundException.class, () -> {
////            userRepository.findByEmail("ashu@gmail");
////        });
////    }
////@Test
////public void getUserByEmailTest() {
//////    userRepository.insert(user);
////    User fetcheduser = userRepository.findByEmail(user.getEmail());
////    Assert.assertEquals(user.getEmail(), fetcheduser.getEmail());
////
////}
////
////
////    @Test
////    public void findAllUserTest() {
//////        userRepository.insert(user);
////        user.setName("Jitu");
//////        userRepository.insert(user);
////        List<User> fetchuser = userRepository.findAll();
////        Assert.assertFalse(fetchuser.isEmpty());
////        Assert.assertNotEquals(fetchuser, null);
////    }
////
//
//
//
//}
