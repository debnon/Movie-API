package com.MovieAPI.service;


import com.MovieAPI.model.User;
import com.MovieAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;


@Component
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String emailID) throws UsernameNotFoundException {
        Optional<User> userRes = userRepository.findByEmailID(emailID);
        if(userRes.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with emailID = " + emailID);
        User user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                user.getEmailID(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }


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


}
