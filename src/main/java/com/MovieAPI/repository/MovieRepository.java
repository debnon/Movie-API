package com.MovieAPI.repository;

import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>, JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);
    List<Movie> findByGenre(Genre genre);
    List<Movie> findByTitleAndGenre(String title, Genre genre);

    @Query("select title from String title where title.genre = :genre and "
            + "(:genre is null or title.genre = :genre)")
    public List<Movie> findByTitleAndOptionalGenre(
            @Param("title") String title,
            @Param("genre") Genre genre);

    // https://stackoverflow.com/questions/32728843/spring-data-optional-parameter-in-query-method

}

