package com.MovieAPI.repository;

import com.MovieAPI.model.Roles;
import com.MovieAPI.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAllUsersReturnsUsers(){
        //Arrange
        User user = new User(1L, false, Roles.ROLE_USER, "user1", "user1123", "user1", "1", "user1@gmail.com", "0748956121");

        //Act
        userRepository.save(user);
        Iterable<User> users =userRepository.findAll();

        //Assert
        assertThat(users).hasSize(1);
    }

    @Test
    public void testCreatesAndFindsUserByIdReturnsUser(){
        //Arrange
        User user = new User(1L, false, Roles.ROLE_USER, "user1", "user1123", "user1", "1", "user1@gmail.com", "0748956121");
        //Act
        userRepository.save(user);
        //Assert
        var userById = userRepository.findById(user.getId());
        assertThat(userById).isNotNull();
    }

    @Test
    public void testFindByEmailIdAndPasswordReturnsUser(){
        //Arrange
        User user = new User(1L, false, Roles.ROLE_USER, "user1", "user1123", "user1", "1", "user1@gmail.com", "0748956121");
        //Act
        userRepository.save(user);
        var user1 = userRepository.findByEmailIDAndPassword(user.getEmailID(), user.getPassword());
        //Assert
        assertThat(user.getEmailID()).isEqualTo(user1.get().getEmailID());
    }

    @Test
    public void testFindByEmailIdReturnsUser(){
        //Arrange
        User user = new User(1L, false, Roles.ROLE_USER, "user1", "user1123", "user1", "1", "user1@gmail.com", "0748956121");
        //Act
        userRepository.save(user);
        var user1 = userRepository.findByEmailID(user.getEmailID());
        //Assert
        assertThat(user.getEmailID()).isEqualTo(user1.get().getEmailID());
    }
}
