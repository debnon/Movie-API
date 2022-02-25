package com.MovieAPI.repository;

import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>, JpaRepository<Movie, Long> {

//    List<Movie> findByTitle(String title);
//    List<Movie> findByDescription(String description);
//    List<Movie> findByReleaseDate(String releaseDate);
//    List<Movie> findByRating(String rating);
//    List<Movie> findByOriginalLanguage(String originalLanguage);
//    List<Movie> findByGenre(Genre genre);
//
//    List<Movie> findByTitleAndGenre(String title, Genre genre);

    Set<Movie> findByTitle(String title);
    Set<Movie> findByDescription(String description);
    Set<Movie> findByReleaseDate(String releaseDate);
    Set<Movie> findByRating(String rating);
    Set<Movie> findByOriginalLanguage(String originalLanguage);
    Set<Movie> findByGenre(Genre genre);

    List<Movie> findByTitleAndGenre(String title, Genre genre);

//    @Query("select title from String title where title.genre = :genre and "
//            + "(:genre is null or title.genre = :genre)")
//    public List<Movie> findByTitleAndOptionalGenre(
//            @Param("title") String title,
//            @Param("genre") Genre genre);

    // https://stackoverflow.com/questions/32728843/spring-data-optional-parameter-in-query-method

}

