package com.MovieAPI.controller;

import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import com.MovieAPI.service.MovieServiceImpl;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class MovieControllerTests {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieServiceImpl mockMovieServiceImpl;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(movieController).build();
        mapper = new ObjectMapper();
    }

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
                        MockMvcRequestBuilders.get("/api/v1/movie/"))
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
                        MockMvcRequestBuilders.post("/api/v1/movie/")
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
                        MockMvcRequestBuilders.get("/api/v1/movie/id/" + movie.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Movie4"));
    }

    @Test
    public void testGetMappingGetMovieByAttributes() throws Exception {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Drama);
        genreList.add(Genre.Crime);

        Movie movie = new Movie(4L, "Movie4", "This is description of Movie4", "12-07-86", "A", "English", genreList, "poster4", "bd4", 0145L, "NA", "400");
        Set<Movie> movies = new HashSet<>();
        movies.add(movie);
        //Act and Assert
        when(mockMovieServiceImpl.getMovieByAttributes(movie.getTitle(), movie.getDescription(), movie.getReleaseDate(),
                movie.getRating(), movie.getOriginalLanguage(), movie.getGenres())).thenReturn(movies);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/criteria/" + "?" + movie.getTitle()
                                + "&" + movie.getDescription() + "&" + movie.getReleaseDate() + "&" + movie.getRating() +
                                "&" + movie.getOriginalLanguage() + "&" + movie.getGenres()))
                .andExpect(MockMvcResultMatchers.status().isOk());
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
                        MockMvcRequestBuilders.put("/api/v1/movie/" + movie.getId())
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

        this.mockMvcController.perform(MockMvcRequestBuilders.delete("/api/v1/movie/" + movie.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //Assert
        verify(mockMovieServiceImpl, times(1)).deleteMovieById(movie.getId());

    }
}
