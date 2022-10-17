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
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
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

    private User user;
    private Address address;

    @BeforeEach
    public void setUp() throws Exception {
        user = new User();
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

    @Test
    void testDeleteUserSuccess() {
//        Optional<User> ofResult = Optional.of(user);
      User user1 = userRepository.findByEmail(user.getEmail());


       doNothing().when(userRepository).deleteByEmail((String) any());
        when(userRepository.findByEmail((String) any())).thenReturn(user1);
        assertSame(user, userServiceImpl.deleteUserByEmail(user.getEmail()));
        verify(userRepository).findByEmail((String) any());
        verify(userRepository).deleteByEmail((String) any());
    }
}

