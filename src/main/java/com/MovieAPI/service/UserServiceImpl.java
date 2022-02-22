package com.MovieAPI.service;


import com.MovieAPI.model.Movie;
import com.MovieAPI.model.User;
import com.MovieAPI.repository.MovieRepository;
import com.MovieAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Component
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

//    public User findByEmailIdAndPassword(String emailId, String password) {
//        //return userRepository.findByEmailIdAndPassword(emailId, password).isPresent() ? userRepository.findByEmailIdAndPassword(emailId, password).get() : null;
//        return userRepository.findByEmailIdAndPassword(emailId, password).orElse(null);
//    }
//
//    public User addUser(String username, String firstname, String lastname, String emailId, String password, String contactnumber) {
//        if (emailId == null || password == null) {
//            return null;
//        } else {
//                if (userRepository.findByEmailId(emailId).isPresent()) {
//                    System.out.println("Duplicate User");
//                    return null;
//                }
//            User user = new User();
//            user.setUsername(username);
//            user.setFirstname(firstname);
//            user.setLastname(lastname);
//            user.setEmailID(emailId);
//            user.setPassword(password);
//            user.setContactNumber(contactnumber);
//            return userRepository.save(user);
//        }
//    }
}
