package com.MovieAPI.controller;

import com.MovieAPI.exception.DuplicateIDException;
import com.MovieAPI.exception.GetEmptyException;
import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import com.MovieAPI.model.User;
import com.MovieAPI.service.AdminService;

import com.MovieAPI.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    MovieService movieService;

    //Controller methods for admin to handle user operations

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        if (users.isEmpty()) {
            throw new GetEmptyException("No users present in the database");
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User existingUser = adminService.getUserById(user.getId());
        if (existingUser != null) {
            throw new DuplicateIDException("There is already a User with the given ID.. Please try with another ID");
        }
        User newUser = adminService.addUser(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user", "/api/v1/admin" + newUser.getId().toString());
        return new ResponseEntity<>(newUser, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = adminService.getUserById(id);
        if (user == null) {
            throw new GetEmptyException("No User is present with the given ID");
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<User> updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
        User existingUser = adminService.getUserById(id);
        if (existingUser == null) {
            throw new GetEmptyException("User not found. Please try to update an existing user");
        }
        adminService.updateUserById(id, user);
        return new ResponseEntity<>(adminService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<User> deleteUserById(@PathVariable("id") Long id) {
        User user = adminService.getUserById(id);
        if (user == null) {
            throw new GetEmptyException("User not found. Please try to delete an existing user");
        }
        adminService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Controller methods for admin to handle movie operations

    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        if (movies.isEmpty()) {
            throw new GetEmptyException("No movies present in the database");
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie existingMovie = movieService.getMovieById(movie.getId());
        if (existingMovie != null) {
            throw new DuplicateIDException("There is already a movie with the given ID.. Please try with another ID");
        }
        Movie newMovie = movieService.insertMovie(movie);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("movie", "/api/v1/admin/movie" + newMovie.getId().toString());
        return new ResponseEntity<>(newMovie, httpHeaders, HttpStatus.CREATED);
    }


    @GetMapping({"/movie/{id}"})
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            throw new GetEmptyException("No movie is present with the given ID");
        }
        return new ResponseEntity(movie, HttpStatus.OK);
    }

//    @GetMapping({"movie/criteria"})
//    public ResponseEntity<Set<Movie>> getMovieByAttributes(
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) String description,
//            @RequestParam(required = false) String releaseDate,
//            @RequestParam(required = false) String rating,
//            @RequestParam(required = false) String originalLanguage,
//            @RequestParam(required = false) List<Genre> genres) {
//
//        Set<Movie> requestedMovies = movieService.getMovieByAttributes(title, description, releaseDate,
//                rating, originalLanguage, genres);
//
//        if (requestedMovies == null) {
//            throw new GetEmptyException("There is no Movie present with that ID");
//        }
//        return new ResponseEntity<>(requestedMovies, HttpStatus.OK);
//    }

    @PutMapping({"/movie/{id}"})
    public ResponseEntity<Movie> updateMovieById(@PathVariable("id") Long id, @RequestBody Movie movie) {
         Movie movie1= movieService.getMovieById(id);
        //Return exception message when user is trying to update a Movie that does not exist
        if (movie1 == null) {
            throw new GetEmptyException("Movie not found. Please try to update a Movie that exists.");
        }
        movieService.updateMovieById(id, movie);
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }

    @DeleteMapping({"/movie/{id}"})
    public ResponseEntity<Movie> deleteMovieById(@PathVariable("id") Long id) {
        Movie movie = movieService.getMovieById(id);
        //Return exception message when user is trying to delete a Movie that does not exist
        if (movie == null) {
            throw new GetEmptyException("Movie not found. Please try to delete a Movie that exists.");
        }
        movieService.deleteMovieById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
