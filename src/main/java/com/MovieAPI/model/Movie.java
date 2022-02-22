package com.MovieAPI.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;


@Table(name = "movies")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    Long id;

    @Column
    String title;

    @Column
    String description;

    @Column
    String releaseDate;

    @Column
    String rating;

    @Column
    String originalLanguage;

    @Column
    Genre genre;

    // runtime

    // director or actors

    //getters and setters https://github.com/eugenp/tutorials/blob/master/persistence-modules/spring-data-jpa-repo/src/main/java/com/baeldung/derivedquery/entity/User.java
}
