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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

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

    @PutMapping({"/{id}"})
    public ResponseEntity<User> updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
        user = adminService.getUserById(id);
        if (user == null) {
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

}
