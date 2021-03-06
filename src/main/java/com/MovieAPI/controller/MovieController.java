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
import java.util.Set;

@RestController
@RequestMapping("/api/v1/movie")
@CrossOrigin("*")
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


    @GetMapping({"/id/{MovieId}"})
    public ResponseEntity<Movie> getMovieById(@PathVariable Long MovieId) {

        Movie Movie = MovieService.getMovieById(MovieId);

        if (Movie == null) {
            throw new GetEmptyException("There is no Movie present with that ID");
        }

        return new ResponseEntity<>(Movie, HttpStatus.OK);
    }


    @GetMapping({"/criteria"})
    public ResponseEntity<Set<Movie>> getMovieByAttributes(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String releaseDate,
            @RequestParam(required = false) String rating,
            @RequestParam(required = false) String originalLanguage,
            @RequestParam(required = false) List<Genre> genres) {

        Set<Movie> requestedMovies = MovieService.getMovieByAttributes(title, description, releaseDate,
                                                                        rating, originalLanguage, genres);

        if (requestedMovies == null) {
            throw new GetEmptyException("There is no Movie present with that ID");
        }

        return new ResponseEntity<>(requestedMovies, HttpStatus.OK);
    }

//    @GetMapping({"/top"})
//    public ResponseEntity<List<Movie>> getTopMovies() {
//
//    }

    @GetMapping({"/curated"})
    public ResponseEntity<List<Movie>> getCuratedMovies(@RequestParam String listNumber) {
        List<Movie> Movies = MovieService.getCuratedMovies(listNumber);

        if (Movies.isEmpty()) {
            throw new GetEmptyException("No curated movies present");
        }

        return new ResponseEntity<>(Movies, HttpStatus.OK);
    }

    @PostMapping({"/curated"})
    public ResponseEntity<Movie> addCuratedMovieByID(@RequestParam Long ID, @RequestParam String listNumber) {
        Movie movie= MovieService.addCuratedMovieByID(ID, listNumber);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    // requestbody format ["id", "id", "id"]
    @PostMapping({"/curated/add"})
    public ResponseEntity<List<Movie>> addCuratedMoviesByIDs(
            @RequestBody ArrayList<String> IDs,
            @RequestParam(required = false)  String listNumber) {
        System.out.println(listNumber);
        List<Movie> Movies= MovieService.addCuratedMoviesByIDs(IDs, listNumber);

        return new ResponseEntity<>(Movies, HttpStatus.OK);
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


    @PutMapping({"/{MovieId}"})
    public ResponseEntity<Movie> updateMovieById(@PathVariable("MovieId") Long MovieId, @RequestBody Movie Movie) {

        Movie = MovieService.getMovieById(MovieId);

        if (Movie == null) {
            throw new GetEmptyException("Movie not found. Please try to update a Movie that exists.");
        }

        MovieService.updateMovieById(MovieId, Movie);
        return new ResponseEntity<>(MovieService.getMovieById(MovieId), HttpStatus.OK);
    }


    @DeleteMapping({"/{MovieId}"})
    public ResponseEntity<Movie> deleteMovieById(@PathVariable("MovieId") Long MovieId) {

        Movie Movie = MovieService.getMovieById(MovieId);

        if (Movie == null) {
            throw new GetEmptyException("Movie not found. Please try to delete a Movie that exists.");
        }

        MovieService.deleteMovieById(MovieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
