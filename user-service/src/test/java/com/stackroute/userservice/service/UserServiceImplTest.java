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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.mockito.Mockito.doThrow;
import org.springframework.test.context.ContextConfiguration;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

        @MockBean
        private MongoOperations mongoOperations;

        @Mock
        private UserRepository userRepository;

        @InjectMocks
        private UserServiceImpl userServiceImpl;

    Optional<User> options;

//    Optional<User> options;

    private User user;
//    private Address address;
//
    @BeforeEach
    public void setUp() throws Exception {


            user = new User();
            user.setName("Ashu");
            user.setPassword("ashutosh@123");
            user.setGender("MALE");
            user.setEmail("ashu@gmail.com");
            user.setPassword("password123");
            user.setContactNo(9876543210L);
//
        Address address=new Address();
        address.setCity("Jeypore");
        address.setLandmark("Near Public School");
        address.setBuildingName("SR Appartments");
        address.setHouseNumber(1);
        address.setStreetName("A");
        address.setPinCode(764001);
        address.setState("Odisha");

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
    void testDeleteUserSuccess() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        boolean flag=userServiceImpl.deleteUserByEmail("ashu@gmail.com");
        assertEquals(true, flag);

    }

    @Test
    void testDeleteUserFailure() {
        Optional<User> ofResult = Optional.of(user);
        doThrow(new UserNotFoundException("Msg")).when(userRepository).deleteByEmail((String) any());
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.deleteUserByEmail("ashu@gmail.com"));
        verify(userRepository).findByEmail((String) any());
        verify(userRepository).deleteByEmail((String) any());

    }

    @Test
    void testAddUserSuccess() {
//        when(userRepository.save((User) any())).thenReturn(user);
//        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        //DatabaseSequence databaseSequence = new DatabaseSequence();
        //databaseSequence.setId("42");
        //databaseSequence.setSeq(1);
        //when(mongoOperations.findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
        // (Class<DatabaseSequence>) any())).thenReturn(databaseSequence);

            when(userRepository.findById(user.getEmail())).thenReturn(options);
            boolean flag=userServiceImpl.deleteUserByEmail(user.getEmail());
            assertEquals(true, flag);
        }





    }








