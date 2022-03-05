package com.MovieAPI.controller;

import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import com.MovieAPI.model.Roles;
import com.MovieAPI.model.User;
import com.MovieAPI.service.MovieServiceImpl;
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

    @Mock
    private MovieServiceImpl mockMovieServiceImpl;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(adminController).build();
        mapper = new ObjectMapper();
    }

    //Test methods for all the user end points
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

    //Test methods for all the movie end points
    @Test
    public void testGetMappingGetAllMovies() throws Exception {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Adventure);
        genreList.add(Genre.Fantasy);

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1L, "Movie1", "This is description of Movie1", "22-02-22", "A", "English", genreList, "poster1", "bd1", 0200L, "NA", "100"));
        movies.add(new Movie(2L, "Movie2", "This is description of Movie2", "19-08-93", "A", "English", genreList, "poster2", "bd2", 0300L, "NA", "200"));
        movies.add(new Movie(3L, "Movie3", "This is description of Movie3", "01-11-18", "A", "English", genreList, "poster3", "bd3", 0145L, "NA", "300"));

        //Act and Assert
        when(mockMovieServiceImpl.getAllMovies()).thenReturn(movies);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/admin/movie/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Movie1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Movie2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].title").value("Movie3"));
    }

    @Test
    public void testPostMappingAddMovie() throws Exception {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Drama);
        genreList.add(Genre.Crime);

        Movie newMovie = new Movie(4L, "Movie4", "This is description of Movie4", "12-07-86", "A", "English", genreList, "poster4", "bd4", 0145L, "NA", "400");

        //Act
        when(mockMovieServiceImpl.insertMovie(newMovie)).thenReturn(newMovie);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/admin/movie/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(newMovie)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        //Assert
        verify(mockMovieServiceImpl, times(1)).insertMovie(newMovie);

    }

    @Test
    public void testGetMappingGetMovieById() throws Exception {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Drama);
        genreList.add(Genre.Crime);

        Movie movie = new Movie(4L, "Movie4", "This is description of Movie4", "12-07-86", "A", "English", genreList, "poster4", "bd4", 0145L, "NA", "400");

        //Act and Assert
        when(mockMovieServiceImpl.getMovieById(movie.getId())).thenReturn(movie);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/admin/movie/" + movie.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Movie4"));
    }

    @Test
    public void testPutMappingUpdateAMovie() throws Exception {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Drama);
        genreList.add(Genre.Crime);

        Movie movie = new Movie(4L, "Movie4", "This is description of Movie4", "12-07-86", "A", "English", genreList, "poster4", "bd4", 0145L, "NA", "400");


        //Act
        when(mockMovieServiceImpl.getMovieById(movie.getId())).thenReturn(movie);
        movie.setTitle("Movie Four");

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.put("/api/v1/admin/movie/" + movie.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //Assert
        verify(mockMovieServiceImpl, times(1)).updateMovieById(movie.getId(), movie);
    }

    @Test
    public void testDeleteMappingDeleteAMovie() throws Exception {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Drama);
        genreList.add(Genre.Crime);

        Movie movie = new Movie(4L, "Movie4", "This is description of Movie4", "12-07-86", "A", "English", genreList, "poster4", "bd4", 0145L, "NA", "400");


        //Act
        when(mockMovieServiceImpl.getMovieById(movie.getId())).thenReturn(movie);

        this.mockMvcController.perform(MockMvcRequestBuilders.delete("/api/v1/admin/movie/" + movie.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //Assert
        verify(mockMovieServiceImpl, times(1)).deleteMovieById(movie.getId());

    }

}
