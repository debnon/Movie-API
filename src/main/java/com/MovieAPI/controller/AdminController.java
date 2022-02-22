package com.MovieAPI.controller;

import com.MovieAPI.exception.DuplicateIDException;
import com.MovieAPI.exception.GetEmptyException;
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

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;


    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User existingUser = adminService.getUserById(user.getId());
        if (existingUser != null) {
            throw new DuplicateIDException("There is already a User with the given ID.. Please try with another ID");
        }
        User newUser = adminService.addUser(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user", "/api/admin" + newUser.getId().toString());
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
//
//    //User Story 4 - Update Movie By Id Solution
//    @PutMapping({"/{MovieId}"})
//    public ResponseEntity<Movie> updateMovieById(@PathVariable("MovieId") Long MovieId, @RequestBody Movie Movie) {
//        Movie = MovieService.getMovieById(MovieId);
//        //Return exception message when user is trying to update a Movie that does not exist
//        if (Movie == null) {
//            throw new GetEmptyException("Movie not found. Please try to update a Movie that exists.");
//        }
//        MovieService.updateMovieById(MovieId, Movie);
//        return new ResponseEntity<>(MovieService.getMovieById(MovieId), HttpStatus.OK);
//    }
//
//    //delete Movie by id
//    @DeleteMapping({"/{MovieId}"})
//    public ResponseEntity<Movie> deleteMovieById(@PathVariable("MovieId") Long MovieId) {
//        Movie Movie = MovieService.getMovieById(MovieId);
//        //Return exception message when user is trying to delete a Movie that does not exist
//        if (Movie == null) {
//            throw new GetEmptyException("Movie not found. Please try to delete a Movie that exists.");
//        }
//        MovieService.deleteMovieById(MovieId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
