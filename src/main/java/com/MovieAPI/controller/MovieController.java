package com.MovieAPI.controller;

import com.MovieAPI.exception.DuplicateIDException;
import com.MovieAPI.exception.GetEmptyException;
import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import com.MovieAPI.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    MovieService MovieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> Movies = MovieService.getAllMovies();
        if (Movies.isEmpty()) {
            throw new GetEmptyException("No Movies present in the database");
        }

        return new ResponseEntity<>(Movies, HttpStatus.OK);
    }

//    @GetMapping({"/id/{MovieId}"})
//    public ResponseEntity<Movie> getMovieById(@PathVariable Long MovieId) {
//        Movie Movie = MovieService.getMovieById(MovieId);
//        //Return exception message when no Movie is present with given id
//        if (Movie == null) {
//            throw new GetEmptyException("There is no Movie present with that ID");
//        }
//        return new ResponseEntity<>(Movie, HttpStatus.OK);
//    }
//
//    @GetMapping({"/title/{title}"})
//    public ResponseEntity<Movie> getMovieById(@PathVariable Long MovieId) {
//        Movie Movie = MovieService.getMovieById(MovieId);
//        //Return exception message when no Movie is present with given id
//        if (Movie == null) {
//            throw new GetEmptyException("There is no Movie present with that ID");
//        }
//        return new ResponseEntity<>(Movie, HttpStatus.OK);
//    }

    @GetMapping({"/criteria"})
    public ResponseEntity<List<Movie>> getMovieByAttributes(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String releaseDate,
            @RequestParam(required = false) String rating,
            @RequestParam(required = false) String originalLanguage,
            @RequestParam(required = false) Genre genre) {

        List<Movie> requestedMovies = MovieService.getMovieByAttributes(title, genre);

        if (requestedMovies == null) {
            throw new GetEmptyException("There is no Movie present with that ID");
        }
        return new ResponseEntity<>(requestedMovies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie Movie) {
        Movie existingMovie = MovieService.getMovieById(Movie.getId());
        if (existingMovie != null) {
            throw new DuplicateIDException("There is already a Movie with the given ID.. Please try with another ID");
        }

        Movie newMovie = MovieService.insertMovie(Movie);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Movie", "/api/v1/Movie/" + newMovie.getId().toString());
        return new ResponseEntity<>(newMovie, httpHeaders, HttpStatus.CREATED);
    }

    //User Story 4 - Update Movie By Id Solution
    @PutMapping({"/{MovieId}"})
    public ResponseEntity<Movie> updateMovieById(@PathVariable("MovieId") Long MovieId, @RequestBody Movie Movie) {
        Movie = MovieService.getMovieById(MovieId);
        //Return exception message when user is trying to update a Movie that does not exist
        if (Movie == null) {
            throw new GetEmptyException("Movie not found. Please try to update a Movie that exists.");
        }
        MovieService.updateMovieById(MovieId, Movie);
        return new ResponseEntity<>(MovieService.getMovieById(MovieId), HttpStatus.OK);
    }

    //delete Movie by id
    @DeleteMapping({"/{MovieId}"})
    public ResponseEntity<Movie> deleteMovieById(@PathVariable("MovieId") Long MovieId) {
        Movie Movie = MovieService.getMovieById(MovieId);
        //Return exception message when user is trying to delete a Movie that does not exist
        if (Movie == null) {
            throw new GetEmptyException("Movie not found. Please try to delete a Movie that exists.");
        }
        MovieService.deleteMovieById(MovieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
