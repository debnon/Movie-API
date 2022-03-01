package com.MovieAPI.service;

import com.MovieAPI.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    User addUser(String username, String firstname, String lastname, String emailID, String password, String contactnumber);
    User findByEmailIDAndPassword(String emailID, String password);
    List<User> getAllUsers();
    void updateUserById(Long id, User user);
    void deleteUserById(Long id);
}
