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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    //Registration Request
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User existingUser = userService.getUserById(user.getId());
        if (existingUser != null) {
            throw new DuplicateIDException("There is already a User with the given ID.. Please try with another ID");
        }
        User newUser = userService.addUser(user.getUsername(), user.getFirstname(),
                user.getLastname(), user.getEmailID(), user.getPassword(), user.getContactnumber());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user", "/api/user" + newUser.getId().toString());
        return new ResponseEntity<>(newUser, httpHeaders, HttpStatus.CREATED);
    }

    //LoginRequest
    @PostMapping("/login")
    public String loginUser(@RequestBody User user, Model model) {
        User existingUser = userService.findByEmailIDAndPassword(user.getEmailID(), user.getPassword());
        if (existingUser != null) {
            model.addAttribute("userLogin", existingUser.getEmailID());
            return "My_Account";
        } else {
            return "Incorrect details, please enter valid emailID and password!";
        }
    }

}
