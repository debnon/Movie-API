package com.MovieAPI.service;

import com.MovieAPI.model.Movie;
import com.techreturners.Moviemanager.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();
    Movie insertMovie(Movie Movie);
    Movie getMovieById(Long id);

    //User Story 4 - Update Movie By Id Solution
    void updateMovieById(Long id, Movie Movie);

    void deleteMovieById(Long id);
}


