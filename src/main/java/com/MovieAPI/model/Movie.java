package com.MovieAPI.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;


@Table(name = "movies")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    Long id;

    @Column
    String title;

    @Column(length = 1000)
    String description;

    @Column
    String releaseDate;

    @Column
    String rating;

    @Column
    String originalLanguage;

    @Column

    @ElementCollection(targetClass=Genre.class)
    List<Genre> genres;


    @Column
    String poster;

    @Column

    String backdrop;

    @Column
    Long runtime;

    @Column
    String releaseStatus;

    @Column
    String imdbID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenre(List<Genre> genres) {
        this.genres = genres;
    }


    // runtime

    // director or actors

    //getters and setters https://github.com/eugenp/tutorials/blob/master/persistence-modules/spring-data-jpa-repo/src/main/java/com/baeldung/derivedquery/entity/User.java
}
