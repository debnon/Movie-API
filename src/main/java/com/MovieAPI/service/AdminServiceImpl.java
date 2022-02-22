package com.MovieAPI.service;

import com.MovieAPI.model.User;
import com.MovieAPI.repository.MovieRepository;
import com.MovieAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService  {

    @Autowired
    UserRepository userRepository;


    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).isPresent()?userRepository.findById(id).get():null;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
