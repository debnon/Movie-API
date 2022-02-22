package com.MovieAPI.service;

import com.MovieAPI.model.User;

public interface UserService {
    User getUserById(Long id);
    User addUser(String username, String firstname, String lastname, String emailID, String password, String contactNumber);
    //User findByEmailIDAndPassword(String emailID, String password);

//    User getUserById(Long id);
//    User addUser(User user);
}
