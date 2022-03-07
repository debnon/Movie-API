package com.MovieAPI.service;

import com.MovieAPI.model.Roles;
import com.MovieAPI.model.User;
import com.MovieAPI.repository.UserRepository;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
public class UserServiceTests {

    @InjectMocks
    private UserServiceImpl mockUserServiceImpl;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserDetails userDetails;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mapper = new ObjectMapper();
    }

    @Test
    public void testLoadByUsername() {
        //Arrange
        User user = new User(1L, false, Roles.ROLE_USER, "user1", "user1123", "user1", "1", "user1@gmail.com", "0748956121");

        //Act
        when(mockUserRepository.findByEmailID(user.getEmailID())).thenReturn(Optional.of(user));

        UserDetails userDetails = mockUserServiceImpl.loadUserByUsername(user.getEmailID());

        //Assert
        verify(mockUserRepository, times(1)).findByEmailID(user.getEmailID());
    }

    @Test
    public void testAllUsersReturnsListOfUsers() {
        //Arrange
        List<User> users = new ArrayList<>();
        users.add(new User(1L, false, Roles.ROLE_USER, "user1", "user1123", "user1", "1", "user1@gmail.com", "0748956121"));
        users.add(new User(2L, true, Roles.ROLE_USER, "user2", "user2123", "user2", "12", "user2@gmail.com", "0748956122"));
        users.add(new User(3L, false, Roles.ROLE_USER, "user3", "user3123", "user3", "123", "user3@gmail.com", "0748956123"));

        //Act
        when(mockUserRepository.findAll()).thenReturn(users);

        List<User> actualResult = mockUserServiceImpl.getAllUsers();

        //Assert
        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(users);
    }

    @Test
    public void testAddAUser() {
        //Arrange
        mapper.disable(MapperFeature.USE_ANNOTATIONS);
        User user = new User();
        user.setUsername("user4");
        user.setFirstname("user4");
        user.setLastname("1234");
        user.setEmailID("user4@gmail.com");
        user.setPassword("user4");
        user.setContactnumber("856923147");

        //Act
        when(mockUserRepository.save(user)).thenReturn(user);

        when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
        User actualResult = mockUserServiceImpl.addUser(user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmailID(), user.getPassword(), user.getContactnumber());

        //Assert
        assertThat(actualResult).isEqualTo(user);
    }

    @Test
    public void testGetUserById() {
        //Arrange
        Long id = 5L;
        var user = new User(5L, false, Roles.ROLE_USER, "user5", "user54123", "user5", "12345", "user5@gmail.com", "0965896241");

        //Act
        when(mockUserRepository.findById(id)).thenReturn(Optional.of(user));

        User actualResult = mockUserServiceImpl.getUserById(id);

        //Assert
        assertThat(actualResult).isEqualTo(user);
    }

    @Test
    public void testFindByEmailIDAndPassword() {
        //Arrange
        var user = new User(5L, false, Roles.ROLE_USER, "user5", "user54123", "user5", "12345", "user5@gmail.com", "0965896241");

        //Act
        when(mockUserRepository.findByEmailIDAndPassword(user.getEmailID(), user.getPassword())).thenReturn(Optional.of(user));

        User actualResult = mockUserServiceImpl.findByEmailIDAndPassword(user.getEmailID(), user.getPassword());

        //Assert
        assertThat(actualResult).isEqualTo(user);
    }

    @Test
    public void testUpdateUserById() {
        //Arrange
        Long id = 5L;
        var user = new User(5L, false, Roles.ROLE_USER, "user5", "user54123", "user5", "12345", "user5@gmail.com", "0965896241");

        //Act
        when(mockUserRepository.findById(id)).thenReturn(Optional.of(user));
        when(mockUserRepository.save(user)).thenReturn(user);

        mockUserServiceImpl.updateUserById(id, user);

        //Assert
        verify(mockUserRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUserById() {
        //Arrange
        Long id = 5L;
        var user = new User(5L, false, Roles.ROLE_USER, "user5", "user54123", "user5", "12345", "user5@gmail.com", "0965896241");

        //Act
        when(mockUserRepository.findById(id)).thenReturn(Optional.of(user));
        doNothing().when(mockUserRepository).deleteById(id);

        mockUserServiceImpl.deleteUserById(id);

        //Assert
        verify(mockUserRepository, times(1)).deleteById(id);
    }


}
