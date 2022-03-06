package com.MovieAPI.repository;

import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MovieRepositoryTests {

    @Autowired
    MovieRepository movieRepository;

    @Test
    public void testFindAllMoviesReturnsMovies() {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Adventure);
        genreList.add(Genre.Fantasy);

        Movie movie = new Movie(1L, "Movie1", "This is description of Movie1", "22-02-22", "A", "English", genreList, "poster1", "bd1", 0200L, "NA", "100");

        //Act
        movieRepository.save(movie);
        Iterable<Movie> movies = movieRepository.findAll();

        //Assert
        assertThat(movies).hasSize(1);
    }

    @Test
    public void testCreatesAndFindsMoviesByIdReturnsMovies() {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Adventure);
        genreList.add(Genre.Fantasy);

        Movie movie = new Movie(1L, "Movie1", "This is description of Movie1", "22-02-22", "A", "English", genreList, "poster1", "bd1", 0200L, "NA", "100");
        //Act
        movieRepository.save(movie);
        var movieById = movieRepository.findById(movie.getId());
        //Assert
        assertThat(movieById).isNotNull();
    }

    @Test
    public void testFindByTitleReturnsMovies() {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Adventure);
        genreList.add(Genre.Fantasy);

        Movie movie = new Movie(1L, "Movie1", "This is description of Movie1", "22-02-22", "A", "English", genreList, "poster1", "bd1", 0200L, "NA", "100");
        //Act
        movieRepository.save(movie);
        var movie1 = movieRepository.findByTitle(movie.getTitle());

        //Assert
        assertThat(movie.getTitle()).isEqualTo(movie1.stream().findAny().get().getTitle());
    }

    @Test
    public void testFindByDescriptionReturnsMovies() {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Adventure);
        genreList.add(Genre.Fantasy);

        Movie movie = new Movie(1L, "Movie1", "This is description of Movie1", "22-02-22", "A", "English", genreList, "poster1", "bd1", 0200L, "NA", "100");

        //Act
        movieRepository.save(movie);
        var movie1 = movieRepository.findByDescription(movie.getDescription());

        //Assert
        assertThat(movie.getDescription()).isEqualTo(movie1.stream().findAny().get().getDescription());
    }

    @Test
    public void findByReleaseDateReturnsMovies() {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Adventure);
        genreList.add(Genre.Fantasy);

        Movie movie = new Movie(1L, "Movie1", "This is description of Movie1", "22-02-22", "A", "English", genreList, "poster1", "bd1", 0200L, "NA", "100");

        //Act
        movieRepository.save(movie);
        var movie1 = movieRepository.findByReleaseDate(movie.getReleaseDate());

        //Assert
        assertThat(movie.getReleaseDate()).isEqualTo(movie1.stream().findAny().get().getReleaseDate());

    }

    @Test
    public void testFindByRatingReturnsMovies() {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Adventure);
        genreList.add(Genre.Fantasy);

        Movie movie = new Movie(1L, "Movie1", "This is description of Movie1", "22-02-22", "A", "English", genreList, "poster1", "bd1", 0200L, "NA", "100");

        //Act
        movieRepository.save(movie);
        var movie1 = movieRepository.findByRating(movie.getRating());

        //Assert
        assertThat(movie.getRating()).isEqualTo(movie1.stream().findAny().get().getRating());
    }

    @Test
    public void testFindByOriginalLanguageReturnsMovies() {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Adventure);
        genreList.add(Genre.Fantasy);

        Movie movie = new Movie(1L, "Movie1", "This is description of Movie1", "22-02-22", "A", "English", genreList, "poster1", "bd1", 0200L, "NA", "100");

        //Act
        movieRepository.save(movie);
        var movie1 = movieRepository.findByOriginalLanguage(movie.getOriginalLanguage());

        //Assert
        assertThat(movie.getOriginalLanguage()).isEqualTo(movie1.stream().findAny().get().getOriginalLanguage());
    }

    @Test
    public void testFindByGenresInReturnsMovies() {
        //Arrange
        List<Genre> genreList = new ArrayList();
        genreList.add(Genre.Adventure);
        genreList.add(Genre.Fantasy);

        Movie movie = new Movie(1L, "Movie1", "This is description of Movie1", "22-02-22", "A", "English", genreList, "poster1", "bd1", 0200L, "NA", "100");

        //Act
        movieRepository.save(movie);
        var movie1 = movieRepository.findByGenresIn(genreList);

        //Assert
        assertThat(movie.getGenres()).isEqualTo(movie1.stream().findAny().get().getGenres());
    }
}
