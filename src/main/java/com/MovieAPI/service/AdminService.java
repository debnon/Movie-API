package com.MovieAPI.service;

import com.MovieAPI.model.User;

public interface AdminService {
    User getUserById(Long id);
    User addUser(User user);
}
