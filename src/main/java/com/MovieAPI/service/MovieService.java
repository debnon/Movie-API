package com.MovieAPI.service;

import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();
    Movie insertMovie(Movie Movie);
    Movie getMovieById(Long id);

//    Movie getMovieByTitle() {
//
//    }

    List<Movie> getMovieByAttributes(String title, Genre genre);

    //User Story 4 - Update Movie By Id Solution
    void updateMovieById(Long id, Movie Movie);

    void deleteMovieById(Long id);
}


