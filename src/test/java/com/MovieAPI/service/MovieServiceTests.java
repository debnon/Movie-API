package com.MovieAPI.service;

import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import com.MovieAPI.repository.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
public class MovieServiceTests {

    @InjectMocks
    private MovieServiceImpl mockMovieServiceImpl;

    @Mock
    private MovieRepository mockMovieRepository;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mapper = new ObjectMapper();
    }

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
    public void testGetMovieByAttributes() {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Fantasy);
        genreList.add(Genre.Adventure);

        Movie movie = new Movie(4L, "Movie5", "This is description of Movie5", "12-07-86", "A", "English", genreList, "poster5", "bd5", 0200L, "NA", "500");
        Set<Movie> movies = new HashSet();
        movies.add(movie);

        //Act
        when(mockMovieRepository.findByTitle(movie.getTitle())).thenReturn(movies);
        Set<Movie> result = mockMovieServiceImpl.getMovieByAttributes(movie.getTitle(), null, null, null, null, null);

        //Assert
        assertThat(movies.size()).isEqualTo(result.size());
    }

    @Test
    public void testDeleteMovieById() {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Fantasy);
        genreList.add(Genre.Adventure);

        Long id = 5L;
        var movie = new Movie(5L, "Movie5", "This is description of Movie5", "12-07-86", "A", "English", genreList, "poster5", "bd5", 0200L, "NA", "500");

        //Act
        when(mockMovieRepository.findById(id)).thenReturn(Optional.of(movie));
        doNothing().when(mockMovieRepository).deleteById(id);

        mockMovieServiceImpl.deleteMovieById(id);

        //Assert
        verify(mockMovieRepository, times(1)).deleteById(id);

    }

}
