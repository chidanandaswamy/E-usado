package com.stackroute.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.userservice.config.Producer;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.Address;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.model.UserDTO;
import com.stackroute.userservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private Producer producer;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#addUser(User)}
     */
    @Test
    void testAddUser() {
        doNothing().when(producer).sendMessageToRabbitMq((UserDTO) any());

        User user = new User();
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");

        User user1 = new User();
        user1.setAddress(new Address());
        user1.setContactNo(1L);
        user1.setEmail("jane.doe@example.org");
        user1.setGender("Gender");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        when(userRepository.save((User) any())).thenReturn(user1);

        User user2 = new User();
        user2.setAddress(new Address());
        user2.setContactNo(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setGender("Gender");
        user2.setName("Name");
        user2.setPassword("iloveyou");
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.addUser(user2));
        verify(userRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#addUser(User)}
     */
    @Test
    void testAddUser2() {
        doNothing().when(producer).sendMessageToRabbitMq((UserDTO) any());
        when(userRepository.findByEmail((String) any())).thenThrow(new UserNotFoundException("Msg"));
        when(userRepository.save((User) any())).thenThrow(new UserNotFoundException("Msg"));

        User user = new User();
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.addUser(user));
        verify(userRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#addUser(User)}
     */
    @Test
    void testAddUser3() {
        doNothing().when(producer).sendMessageToRabbitMq((UserDTO) any());
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(user).setAddress((Address) any());
        doNothing().when(user).setContactNo(anyLong());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setGender((String) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");

        User user1 = new User();
        user1.setAddress(new Address());
        user1.setContactNo(1L);
        user1.setEmail("jane.doe@example.org");
        user1.setGender("Gender");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        when(userRepository.save((User) any())).thenReturn(user1);

        User user2 = new User();
        user2.setAddress(new Address());
        user2.setContactNo(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setGender("Gender");
        user2.setName("Name");
        user2.setPassword("iloveyou");
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.addUser(user2));
        verify(userRepository).findByEmail((String) any());
        verify(user).getEmail();
        verify(user).setAddress((Address) any());
        verify(user).setContactNo(anyLong());
        verify(user).setEmail((String) any());
        verify(user).setGender((String) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#addUser(User)}
     */
    @Test
    void testAddUser4() {
        doNothing().when(producer).sendMessageToRabbitMq((UserDTO) any());
        User user = mock(User.class);
        when(user.getEmail()).thenReturn(null);
        doNothing().when(user).setAddress((Address) any());
        doNothing().when(user).setContactNo(anyLong());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setGender((String) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");

        User user1 = new User();
        user1.setAddress(new Address());
        user1.setContactNo(1L);
        user1.setEmail("jane.doe@example.org");
        user1.setGender("Gender");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        when(userRepository.save((User) any())).thenReturn(user1);

        User user2 = new User();
        user2.setAddress(new Address());
        user2.setContactNo(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setGender("Gender");
        user2.setName("Name");
        user2.setPassword("iloveyou");
        assertSame(user1, userServiceImpl.addUser(user2));
        verify(producer).sendMessageToRabbitMq((UserDTO) any());
        verify(userRepository).findByEmail((String) any());
        verify(userRepository).save((User) any());
        verify(user).getEmail();
        verify(user).setAddress((Address) any());
        verify(user).setContactNo(anyLong());
        verify(user).setEmail((String) any());
        verify(user).setGender((String) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#findAllUsers()}
     */
    @Test
    void testFindAllUsers() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.findAllUsers());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#findAllUsers()}
     */
    @Test
    void testFindAllUsers2() {
        User user = new User();
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("User Not Found !!");
        user.setName("User Not Found !!");
        user.setPassword("iloveyou");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        List<User> actualFindAllUsersResult = userServiceImpl.findAllUsers();
        assertSame(userList, actualFindAllUsersResult);
        assertEquals(1, actualFindAllUsersResult.size());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#findAllUsers()}
     */
    @Test
    void testFindAllUsers3() {
        when(userRepository.findAll()).thenThrow(new UserNotFoundException("User Not Found !!"));
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.findAllUsers());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#findByEmail(String)}
     */
    @Test
    void testFindByEmail() {
        User user = new User();
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        assertSame(user, userServiceImpl.findByEmail("jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#findByEmail(String)}
     */
    @Test
    void testFindByEmail2() {
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(user).setAddress((Address) any());
        doNothing().when(user).setContactNo(anyLong());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setGender((String) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        userServiceImpl.findByEmail("jane.doe@example.org");
        verify(userRepository).findByEmail((String) any());
        verify(user).getEmail();
        verify(user).setAddress((Address) any());
        verify(user).setContactNo(anyLong());
        verify(user).setEmail((String) any());
        verify(user).setGender((String) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#findByEmail(String)}
     */
    @Test
    void testFindByEmail3() {
        User user = mock(User.class);
        when(user.getEmail()).thenReturn(null);
        doNothing().when(user).setAddress((Address) any());
        doNothing().when(user).setContactNo(anyLong());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setGender((String) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.findByEmail("jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
        verify(user).getEmail();
        verify(user).setAddress((Address) any());
        verify(user).setContactNo(anyLong());
        verify(user).setEmail((String) any());
        verify(user).setGender((String) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUserByEmail(String)}
     */
    @Test
    void testDeleteUserByEmail() {
        User user = new User();
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");
        doNothing().when(userRepository).deleteByEmail((String) any());
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        assertTrue(userServiceImpl.deleteUserByEmail("jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
        verify(userRepository).deleteByEmail((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUserByEmail(String)}
     */
    @Test
    void testDeleteUserByEmail2() {
        User user = new User();
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");
        doThrow(new UserNotFoundException("Msg")).when(userRepository).deleteByEmail((String) any());
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.deleteUserByEmail("jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
        verify(userRepository).deleteByEmail((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUserByEmail(String)}
     */
    @Test
    void testDeleteUserByEmail3() {
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(user).setAddress((Address) any());
        doNothing().when(user).setContactNo(anyLong());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setGender((String) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");
        doNothing().when(userRepository).deleteByEmail((String) any());
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        assertTrue(userServiceImpl.deleteUserByEmail("jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
        verify(userRepository).deleteByEmail((String) any());
        verify(user).getEmail();
        verify(user).setAddress((Address) any());
        verify(user).setContactNo(anyLong());
        verify(user).setEmail((String) any());
        verify(user).setGender((String) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUserByEmail(String)}
     */
    @Test
    void testDeleteUserByEmail4() {
        User user = mock(User.class);
        when(user.getEmail()).thenReturn(null);
        doNothing().when(user).setAddress((Address) any());
        doNothing().when(user).setContactNo(anyLong());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setGender((String) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");
        doNothing().when(userRepository).deleteByEmail((String) any());
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.deleteUserByEmail("jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
        verify(user).getEmail();
        verify(user).setAddress((Address) any());
        verify(user).setContactNo(anyLong());
        verify(user).setEmail((String) any());
        verify(user).setGender((String) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#UpdateByEmail(User, String)}
     */
    @Test
    void testUpdateByEmail() {
        User user = new User();
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");

        User user1 = new User();
        user1.setAddress(new Address());
        user1.setContactNo(1L);
        user1.setEmail("jane.doe@example.org");
        user1.setGender("Gender");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        when(userRepository.save((User) any())).thenReturn(user1);
        when(userRepository.findByEmail((String) any())).thenReturn(user);

        User user2 = new User();
        user2.setAddress(new Address());
        user2.setContactNo(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setGender("Gender");
        user2.setName("Name");
        user2.setPassword("iloveyou");
        assertSame(user1, userServiceImpl.UpdateByEmail(user2, "jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
        verify(userRepository).save((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#UpdateByEmail(User, String)}
     */
    @Test
    void testUpdateByEmail2() {
        User user = new User();
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");
        when(userRepository.save((User) any())).thenThrow(new UserNotFoundException("Msg"));
        when(userRepository.findByEmail((String) any())).thenReturn(user);

        User user1 = new User();
        user1.setAddress(new Address());
        user1.setContactNo(1L);
        user1.setEmail("jane.doe@example.org");
        user1.setGender("Gender");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.UpdateByEmail(user1, "jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
        verify(userRepository).save((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#UpdateByEmail(User, String)}
     */
    @Test
    void testUpdateByEmail3() {
        User user = new User();
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");

        User user1 = new User();
        user1.setAddress(new Address());
        user1.setContactNo(1L);
        user1.setEmail("jane.doe@example.org");
        user1.setGender("Gender");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        when(userRepository.save((User) any())).thenReturn(user1);
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        User user2 = mock(User.class);
        when(user2.getAddress()).thenReturn(new Address());
        doNothing().when(user2).setAddress((Address) any());
        doNothing().when(user2).setContactNo(anyLong());
        doNothing().when(user2).setEmail((String) any());
        doNothing().when(user2).setGender((String) any());
        doNothing().when(user2).setName((String) any());
        doNothing().when(user2).setPassword((String) any());
        user2.setAddress(new Address());
        user2.setContactNo(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setGender("Gender");
        user2.setName("Name");
        user2.setPassword("iloveyou");
        assertSame(user1, userServiceImpl.UpdateByEmail(user2, "jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
        verify(userRepository).save((User) any());
        verify(user2).getAddress();
        verify(user2).setAddress((Address) any());
        verify(user2).setContactNo(anyLong());
        verify(user2).setEmail((String) any());
        verify(user2).setGender((String) any());
        verify(user2).setName((String) any());
        verify(user2).setPassword((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#UpdateByEmail(User, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateByEmail4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.stackroute.userservice.service.UserServiceImpl.UpdateByEmail(UserServiceImpl.java:84)
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setAddress(new Address());
        user.setContactNo(1L);
        user.setEmail("jane.doe@example.org");
        user.setGender("Gender");
        user.setName("Name");
        user.setPassword("iloveyou");

        User user1 = new User();
        user1.setAddress(new Address());
        user1.setContactNo(1L);
        user1.setEmail("jane.doe@example.org");
        user1.setGender("Gender");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        when(userRepository.save((User) any())).thenReturn(user1);
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        User user2 = mock(User.class);
        when(user2.getAddress()).thenReturn(null);
        doNothing().when(user2).setAddress((Address) any());
        doNothing().when(user2).setContactNo(anyLong());
        doNothing().when(user2).setEmail((String) any());
        doNothing().when(user2).setGender((String) any());
        doNothing().when(user2).setName((String) any());
        doNothing().when(user2).setPassword((String) any());
        user2.setAddress(new Address());
        user2.setContactNo(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setGender("Gender");
        user2.setName("Name");
        user2.setPassword("iloveyou");
        userServiceImpl.UpdateByEmail(user2, "jane.doe@example.org");
    }
}

