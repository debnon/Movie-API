package com.MovieAPI.controller;

import com.MovieAPI.exception.CustomException;
import com.MovieAPI.exception.DuplicateIDException;
import com.MovieAPI.exception.GetEmptyException;
import com.MovieAPI.filter.JWTUtil;
import com.MovieAPI.model.User;
import com.MovieAPI.repository.UserRepository;
import com.MovieAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    UserRepository userRepo;

    @GetMapping("/info")
    public User getUserDetails(){
        // Retrieve email from the Security Context
        String emailID = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Fetch and return user details
        return userRepo.findByEmailID(emailID).get();
    }

    //Registration Request
    @PostMapping("/registration")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User existingUser = userService.getUserById(user.getId());
        if (existingUser != null) {
            throw new DuplicateIDException("There is already a User with the given ID.. Please try with another ID");
        }
        User newUser = userService.addUser(user.getUsername(), user.getFirstname(),
                user.getLastname(), user.getEmailID(), user.getPassword(), user.getContactnumber());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user", "/api/v1/user" + newUser.getId().toString());
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

    @PostMapping("/authenticate")
    public Map<String, Object> loginHandler(@RequestBody User user) {
        try {
            // Creating the Authentication Token which will contain the credentials for authenticating
            // This token is used as input to the authentication process
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(user.getEmailID(), user.getPassword());

            // Authenticating the Login Credentials
            authManager.authenticate(authInputToken);

            // If this point is reached it means Authentication was successful
            // Generate the JWT
            String token = jwtUtil.generateToken(user.getEmailID());

            // Respond with the JWT
            return Collections.singletonMap("jwt-token", token);
        } catch (AuthenticationException authExc){
            // Auhentication Failed
            throw new RuntimeException("Invalid Login Credentials");
        }
    }

}
