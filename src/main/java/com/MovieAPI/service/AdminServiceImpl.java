package com.MovieAPI.service;

import com.MovieAPI.model.User;
import com.MovieAPI.repository.MovieRepository;
import com.MovieAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public void updateUserById(Long id, User user) {
        User existingUser = userRepository.findById(id).get();
        existingUser.setEmailID(user.getEmailID());
        existingUser.setContactnumber(user.getContactnumber());
        existingUser.setPassword(user.getPassword());

        userRepository.save(existingUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }


}
