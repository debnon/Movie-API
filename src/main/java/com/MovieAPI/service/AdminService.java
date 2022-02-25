package com.MovieAPI.service;

import com.MovieAPI.model.User;

import java.util.List;

public interface AdminService {
    User getUserById(Long id);
    User addUser(User user);
    List<User> getAllUsers();
    void updateUserById(Long id, User user);
    void deleteUserById(Long id);
}
