package com.MovieAPI.repository;

import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>, JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);
    List<Movie> findByGenre(Genre genre);

}

