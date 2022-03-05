package com.MovieAPI.controller;

import com.MovieAPI.model.Roles;
import com.MovieAPI.model.User;
import com.MovieAPI.service.UserServiceImpl;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class AdminControllerTests {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private UserServiceImpl mockUserServiceImpl;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(adminController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testGetMappingGetAllUsers() throws Exception {
        //Arrange
        List<User> users = new ArrayList<>();
        users.add(new User(1L, false, Roles.ROLE_USER, "user1", "user1123", "user1", "1", "user1@gmail.com", "0748956121"));
        users.add(new User(2L, true, Roles.ROLE_USER, "user2", "user2123", "user2", "12", "user2@gmail.com", "0748956122"));
        users.add(new User(3L, false, Roles.ROLE_USER, "user3", "user3123", "user3", "123", "user3@gmail.com", "0748956123"));

        //Act and Assert
        when(mockUserServiceImpl.getAllUsers()).thenReturn(users);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/admin/user/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("user1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].username").value("user2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].username").value("user3"));
    }

    @Test
    public void testPostMappingAddUser() throws Exception {
        //Arrange
        mapper.disable(MapperFeature.USE_ANNOTATIONS);
        User newUser = new User(4L, true, Roles.ROLE_USER, "user4", "user4123", "user4", "1234", "user4@gmail.com", "0748956124");

        //Act
        when(mockUserServiceImpl.addUser(newUser.getUsername(), newUser.getFirstname(), newUser.getLastname(), newUser.getEmailID(), newUser.getPassword(), newUser.getContactnumber())).thenReturn(newUser);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/admin/user/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(newUser)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        //Assert
        verify(mockUserServiceImpl, times(1)).addUser(newUser.getUsername(), newUser.getFirstname(), newUser.getLastname(),
                newUser.getEmailID(), newUser.getPassword(), newUser.getContactnumber());

    }

    @Test
    public void testGetMappingGetUserById() throws Exception {
        //Arrange
        User user = new User(4L, true, Roles.ROLE_USER, "user4", "user4123", "user4", "1234", "user4@gmail.com", "0748956124");

        //Act and Assert
        when(mockUserServiceImpl.getUserById(user.getId())).thenReturn(user);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/admin/user/" + user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("user4"));
    }

    @Test
    public void testPutMappingUpdateAUser() throws Exception {
        //Arrange
        mapper.disable(MapperFeature.USE_ANNOTATIONS);
        User user = new User(4L, true, Roles.ROLE_USER, "user4", "user4123", "user4", "1234", "user4@gmail.com", "0748956124");

        //Act
        when(mockUserServiceImpl.getUserById(user.getId())).thenReturn(user);
        user.setEmailID("user4123@gmail.com");

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.put("/api/v1/admin/user/" + user.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //Assert
        verify(mockUserServiceImpl, times(1)).updateUserById(user.getId(), user);
    }

    @Test
    public void testDeleteMappingDeleteAUser() throws Exception {
        //Arrange
        User user = new User(1L, false, Roles.ROLE_USER, "USER", "user1123", "user1", "1", "user1@gmail.com", "0748956121");

        //Act
        when(mockUserServiceImpl.getUserById(user.getId())).thenReturn(user);

        this.mockMvcController.perform(MockMvcRequestBuilders.delete("/api/v1/admin/user/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //Assert
        verify(mockUserServiceImpl, times(1)).deleteUserById(user.getId());

    }


}
