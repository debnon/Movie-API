package com.MovieAPI.service;

import com.MovieAPI.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Service
public interface UserService {
//    User getUserById(Long Id);
//    User addUser(String username, String firstname, String lastname, String emailId, String password, String contactnumber);
//    User findByEmailIdAndPassword(String emailId, String password);

    User getUserById(Long id);
    User addUser(User user);
}
