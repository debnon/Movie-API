package com.MovieAPI.controller;

import com.MovieAPI.exception.DuplicateIDException;
import com.MovieAPI.exception.GetEmptyException;
import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import com.MovieAPI.model.User;
import com.MovieAPI.service.AdminService;

import com.MovieAPI.service.MovieService;
import com.MovieAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    MovieService movieService;

    //Controller methods for admin to handle user operations

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User existingUser = userService.getUserById(user.getId());
        if (existingUser != null) {
            throw new DuplicateIDException("There is already a User with the given ID.. Please try with another ID");
        }
        User newUser = userService.addUser(user.getUsername(), user.getFirstname(), user.getLastname()
                , user.getEmailID(), user.getPassword(), user.getContactnumber());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user", "/api/v1/admin" + newUser.getId().toString());
        return new ResponseEntity<>(newUser, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new GetEmptyException("No users present in the database");
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping({"/user/{id}"})
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new GetEmptyException("No User is present with the given ID");
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }


    @PutMapping({"/user/{id}"})
    public ResponseEntity<User> updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
        User existingUser = userService.getUserById(id);
        if (existingUser == null) {
            throw new GetEmptyException("User not found. Please try to update an existing user");
        }
        userService.updateUserById(id, user);
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping({"/user/{id}"})
    public ResponseEntity<User> deleteUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new GetEmptyException("User not found. Please try to delete an existing user");
        }
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Controller methods for admin to handle movie operations

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


    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        if (movies.isEmpty()) {
            throw new GetEmptyException("No movies present in the database");
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping({"/movie/{id}"})
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            throw new GetEmptyException("No movie is present with the given ID");
        }
        return new ResponseEntity(movie, HttpStatus.OK);
    }

    @PutMapping({"/movie/{id}"})
    public ResponseEntity<Movie> updateMovieById(@PathVariable("id") Long id, @RequestBody Movie movie) {
        Movie movie1 = movieService.getMovieById(id);
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
