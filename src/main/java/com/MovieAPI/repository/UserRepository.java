package com.MovieAPI.repository;

import com.MovieAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

//   Optional <User> findByEmailIdAndPassword(String emailId, String password);
//   Optional <User>  findByEmailId(String emailId);

}
