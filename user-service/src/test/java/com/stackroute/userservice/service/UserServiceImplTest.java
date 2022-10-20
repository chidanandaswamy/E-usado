package com.stackroute.userservice.service;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.Address;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

        @MockBean
        private MongoOperations mongoOperations;

        @MockBean
        private UserRepository userRepository;

        @Autowired
        private UserServiceImpl userServiceImpl;

    Optional<User> options;

    private User user;
    private Address address;

    @BeforeEach
    public void setUp() throws Exception {
       User user = new User();
        address=new Address();
        user.setName("Ashu");
        user.setEmail("ashu@gmail.com");
        user.setPassword("password123");
        user.setContactNo(9876543210L);
        user.setGender("male");

        address.setHouseNumber(1);
        address.setBuildingName("A");
        address.setStreetName("Surya Mahal");
        address.setLandmark("Near SR Mall");
        address.setPinCode(764001);
        address.setCity("Jeypore");
        address.setState("Odisha");

//        userRepository.deleteByEmail("ashu@gmail.com");
    }


    @Test
    void testGetAllUsersFailure() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.findAllUsers());
        verify(userRepository).findAll();
    }

    @Test
    void testGetAllUsersSuccess() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        List<User> actualAllUsers = userServiceImpl.findAllUsers();
        assertSame(userList, actualAllUsers);
        assertEquals(1, actualAllUsers.size());
        verify(userRepository).findAll();
    }

    @Test
    void testFindByEmailSuccess() {
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        assertSame(user, userServiceImpl.findByEmail("ashu@gmail.com"));
        verify(userRepository).findByEmail((String) any());
    }


    @Test
    void testFindByEmailFailure() {
        when(userRepository.findByEmail((String) any()))
                .thenThrow(new UserNotFoundException("User Not Found !!"));
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.findByEmail("ashu@gmail.com"));
        verify(userRepository).findByEmail((String) any());
    }

    @Test
    public void deleteUserSuccess() throws UserNotFoundException {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        boolean flag = userServiceImpl.deleteUserByEmail(user.getEmail());
        assertEquals(true, flag);

    }

    @Test
    public void updateUser() throws UserNotFoundException {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        user.setPassword("password12345");
        user.setContactNo(9876543560L);
        address.setHouseNumber(2);
        address.setBuildingName("B");
        address.setStreetName("SuryB Mahal");
        address.setLandmark("Near SRI Mall");
        address.setPinCode(764003);
        address.setCity("ASD");
        address.setState("Asdf");
        user.setAddress(address);
        User fetchuser = userServiceImpl.UpdateByEmail(user,user.getEmail());
        System.out.println(fetchuser);
        assertEquals(user, fetchuser);

    }

//    @Test
//    public void registerUserSuccess() throws UserNotFoundException {
//        when(userRepository.insert((User) any())).thenReturn(user);
//        User userSaved = userServiceImpl.addUser(user);
//        assertEquals(user, userSaved);
//
//    }



//    @Test
//    void testDeleteUserSuccess() {
//        Optional<User> ofResult = Optional.of(user);
////
//        doNothing().when(userRepository).deleteByEmail((String) any());
//        when(userRepository.findByEmail((String) any())).thenReturn(ofResult);
//        assertSame(user, userServiceImpl.deleteUserByEmail("ashu@gmail.com"));
//        verify(userRepository).findByEmail((String) any());
//        verify(userRepository).deleteByEmail((String) any());
//    }

//    @Test
//    void testDeleteUserSuccess() {
//        Optional<User> ofResult = Optional.of(user);
////      User user1 = userRepository.findByEmail(user.getEmail());
//
//
//       doNothing().when(userRepository).deleteByEmail((String) any());
//        when(userRepository.findByEmail((String) any())).thenReturn(ofResult);
//        assertSame(user, userServiceImpl.deleteUserByEmail(user.getEmail()));
//        verify(userRepository).findByEmail((String) any());
//        verify(userRepository).deleteByEmail((String) any());
//    }


//    @Test
//    public void updateusertest() throws UserNotFoundException
//    {
//        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
//        user.setName("A");
//        user.setContactNo(987373678l);
////        user.setAddress(new Address("lo", "ko", "ki", "ji"));
//        address.setHouseNumber(2);
//        address.setBuildingName("AP");
//        address.setStreetName("Surya Mahal");
//        address.setLandmark("Near Public School");
//        address.setPinCode(764002);
//        address.setCity("ASD");
//        address.setState("Ogfjh");
//        User fetch=userServiceImpl.UpdateByEmail(user, user.getEmail());
//        assertEquals(user, fetch);
//    }






    }



