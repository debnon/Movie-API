package com.MovieAPI.controller;

import com.MovieAPI.filter.JWTUtil;
import com.MovieAPI.model.Roles;
import com.MovieAPI.model.User;
import com.MovieAPI.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTests {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl mockUserServiceImpl;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @Mock
    JWTUtil jwtUtil;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(userController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testPostMappingAddUser() throws Exception {
        //Arrange
        mapper.disable(MapperFeature.USE_ANNOTATIONS);
        User newUser = new User(4L, true, Roles.ROLE_USER, "user4", "user4123", "user4", "1234", "user4@gmail.com", "0748956124");

        //Act
        when(mockUserServiceImpl.addUser(newUser.getUsername(), newUser.getFirstname(), newUser.getLastname(), newUser.getEmailID(), newUser.getPassword(), newUser.getContactnumber())).thenReturn(newUser);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/user/registration/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(newUser)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        //Assert
        verify(mockUserServiceImpl, times(1)).addUser(newUser.getUsername(), newUser.getFirstname(), newUser.getLastname(),
                newUser.getEmailID(), newUser.getPassword(), newUser.getContactnumber());

    }


    @Test
    public void testPostMappingAuthenticateUser() throws Exception {
        //Arrange
        User user = new User(4L, true, Roles.ROLE_USER, "user4", "user4123", "user4", "1234", "user4@gmail.com", "0748956124");

        //Act
        when(jwtUtil.generateToken(user.getEmailID())).thenReturn("token");

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/user/authenticate/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //Assert
        verify(jwtUtil, times(1)).generateToken(user.getEmailID());
    }
}
