package com.MovieAPI.service;


import com.MovieAPI.model.User;
import com.MovieAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    public UserServiceImpl (UserRepository userRepository){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }

//    @Override
//    public User addUser(User user) {
//        return userRepository.save(user);
//    }

    public User findByEmailIDAndPassword(String emailID, String password) {
        //return userRepository.findByEmailIdAndPassword(emailID, password).isPresent() ? userRepository.findByEmailIdAndPassword(emailID, password).get() : null;
        return userRepository.findByEmailIDAndPassword(emailID, password).orElse(null);
    }

    public User addUser(String username, String firstname, String lastname, String emailID, String password, String contactnumber) {
        if (emailID == null || password == null) {
            return null;
        } else {
            if (userRepository.findByEmailID(emailID).isPresent()) {
                System.out.println("Duplicate User");
                return null;
            }
            User user = new User();
            user.setUsername(username);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setEmailID(emailID);
            String encodedPassword = this.passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setContactnumber(contactnumber);
            return userRepository.save(user);
        }
    }
}
