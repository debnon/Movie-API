package com.MovieAPI.service;

import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import com.MovieAPI.model.Roles;
import com.MovieAPI.model.User;
import com.MovieAPI.repository.MovieRepository;
import com.MovieAPI.repository.UserRepository;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
public class AdminServiceTests {

    @InjectMocks
    private UserServiceImpl mockUserServiceImpl;

    @Mock
    private UserRepository mockUserRepository;

    @InjectMocks
    private MovieServiceImpl mockMovieServiceImpl;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    private MovieRepository mockMovieRepository;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mapper = new ObjectMapper();
    }

    // Test methods for all user operations
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

    //Test methods for all movie operations
    @Test
    public void testAllMoviesReturnsListOfMovies() {

        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Adventure);
        genreList.add(Genre.Fantasy);

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1L, "Movie1", "This is description of Movie1", "22-02-22", "A", "English", genreList, "poster1", "bd1", 0200L, "NA", "100"));
        movies.add(new Movie(2L, "Movie2", "This is description of Movie2", "19-08-93", "A", "English", genreList, "poster2", "bd2", 0300L, "NA", "200"));
        movies.add(new Movie(3L, "Movie3", "This is description of Movie3", "01-11-18", "A", "English", genreList, "poster3", "bd3", 0145L, "NA", "300"));

        //Act
        when(mockMovieRepository.findAll()).thenReturn(movies);

        List<Movie> actualResult = mockMovieServiceImpl.getAllMovies();

        //Assert
        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);

    }

    @Test
    public void testAddAMovie() {

        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Drama);
        genreList.add(Genre.Crime);

        Movie newMovie = new Movie(4L, "Movie4", "This is description of Movie4", "12-07-86", "A", "English", genreList, "poster4", "bd4", 0145L, "NA", "400");

        //Act
        when(mockMovieRepository.save(newMovie)).thenReturn(newMovie);

        Movie actualResult = mockMovieServiceImpl.insertMovie(newMovie);

        //Assert
        assertThat(actualResult).isEqualTo(newMovie);
    }

    @Test
    public void testGetMovieById() {

        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Fantasy);
        genreList.add(Genre.Crime);

        Long id = 5L;
        var movie = new Movie(4L, "Movie5", "This is description of Movie5", "12-07-86", "A", "English", genreList, "poster5", "bd5", 0200L, "NA", "500");

        //Act
        when(mockMovieRepository.findById(id)).thenReturn(Optional.of(movie));

        Movie actualResult = mockMovieServiceImpl.getMovieById(id);

        //Assert
        assertThat(actualResult).isEqualTo(movie);

    }

    @Test
    public void testUpdateMovieById() {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Fantasy);
        genreList.add(Genre.Adventure);

        Long id = 5L;
        var movie = new Movie(4L, "Movie5", "This is description of Movie5", "12-07-86", "A", "English", genreList, "poster5", "bd5", 0200L, "NA", "500");

        //Act
        when(mockMovieRepository.findById(id)).thenReturn(Optional.of(movie));
        when(mockMovieRepository.save(movie)).thenReturn(movie);

        mockMovieServiceImpl.updateMovieById(id, movie);

        //Assert
        verify(mockMovieRepository, times(1)).save(movie);
    }

    @Test
    public void testDeleteMovieById() {

        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Fantasy);
        genreList.add(Genre.Adventure);

        Long id = 5L;
        var movie = new Movie(4L, "Movie5", "This is description of Movie5", "12-07-86", "A", "English", genreList, "poster5", "bd5", 0200L, "NA", "500");

        //Act
        when(mockMovieRepository.findById(id)).thenReturn(Optional.of(movie));
        doNothing().when(mockMovieRepository).deleteById(id);

        mockMovieServiceImpl.deleteMovieById(id);

        //Assert
        verify(mockMovieRepository, times(1)).deleteById(id);

    }

}
