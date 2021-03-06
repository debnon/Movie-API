package com.MovieAPI.service;

import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;

import java.util.List;
import java.util.Set;

public interface MovieService {

    List<Movie> getAllMovies();
    Movie insertMovie(Movie Movie);
    Movie getMovieById(Long id);
    List<Movie> getCuratedMovies(String listNumber);

    Set<Movie> getMovieByAttributes(String title, String description, String releaseDate,
                                    String rating, String originalLanguage, List<Genre> genres);

    //User Story 4 - Update Movie By Id Solution
    void updateMovieById(Long id, Movie Movie);

    void deleteMovieById(Long id);
    Iterable<Movie> listed();
    Iterable<Movie> save(List<Movie> movies);

    Movie addCuratedMovieByID(Long id, String listNumber);

    List<Movie> addCuratedMoviesByIDs(List<String> IDs, String listNumber);
}


