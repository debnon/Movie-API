package com.MovieAPI.controller;

import com.MovieAPI.exception.DuplicateIDException;
import com.MovieAPI.model.User;
import com.MovieAPI.service.MovieService;
import com.MovieAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

//    @PostMapping
//    public ResponseEntity<User> addUser(@RequestBody User user) {
//        User existingUser = userService.getUserById(user.getId());
//        if (existingUser != null) {
//            throw new DuplicateIDException("There is already a User with the given ID.. Please try with another ID");
//        }
//        User newUser = userService.addUser(user);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("user", "/api/admin" + newUser.getId().toString());
//        return new ResponseEntity<>(newUser, httpHeaders, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User existingUser = userService.getUserById(user.getId());
        if (existingUser != null) {
            throw new DuplicateIDException("There is already a User with the given ID.. Please try with another ID");
        }
        User newUser = userService.addUser(user.getUsername(), user.getFirstname(),
                user.getLastname(), user.getEmailID(), user.getPassword(), user.getContactNumber());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user", "/api/admin" + newUser.getId().toString());
        return new ResponseEntity<>(newUser, httpHeaders, HttpStatus.CREATED);
    }



}
