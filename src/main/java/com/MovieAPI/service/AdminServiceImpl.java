package com.MovieAPI.service;

import com.MovieAPI.model.User;
import com.MovieAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    public AdminServiceImpl(UserRepository userRepository){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public void updateUserById(Long id, User user) {
        User existingUser = userRepository.findById(id).get();

        existingUser.setUsername(user.getUsername());
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        existingUser.setPassword(encodedPassword);
        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setEmailID(user.getEmailID());
        existingUser.setContactnumber(user.getContactnumber());

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
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }


}
