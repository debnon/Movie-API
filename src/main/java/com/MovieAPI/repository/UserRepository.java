package com.MovieAPI.repository;

import com.MovieAPI.model.Movie;
import com.MovieAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>, JpaRepository <User, Long> {

   Optional <User> findByEmailIDAndPassword(String emailID, String password);
   Optional <User> findByEmailID(String emailID);

}
