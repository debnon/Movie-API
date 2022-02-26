package com.MovieAPI.controller;

import com.MovieAPI.constants.Constants;
import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import com.MovieAPI.responsemodel.Movies;
import com.MovieAPI.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/tmdb")
public class TMDBApiController {

    Long id;
    String title;
    String description;
    String releaseDate;
    String rating;
    String originalLanguage;
    Genre genre;

    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    public Movies getMoviesFromTmdb() {
        String url = "https://api.themoviedb.org/3/discover/movie?api_key="
                + Constants.TMDB_API_KEY +
                "&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&" +
                "page=1&with_watch_monetization_types=flatrate";
        RestTemplate restTemplate = new RestTemplate();

        Movies movies = restTemplate.getForObject(url, Movies.class);
        System.out.println(movies.getPage());
        System.out.println(movies.getResults().length);
        for (int i = 0; i <= movies.getResults().length - 1; i++) {
            System.out.println(movies.getResults()[i].getId());
            System.out.println(movies.getResults()[i].getOriginal_title());
            System.out.println(movies.getResults()[i].getRelease_date());
            System.out.println(movies.getResults()[i].getPopularity());
            System.out.println(movies.getResults()[i].getOriginal_language());
            System.out.println(movies.getResults()[i].getGenre_ids());

            this.id = movies.getResults()[i].getId();
            this.title = movies.getResults()[i].getOriginal_title();
            //this.description = movies.getResults()[i].getOverview();
            this.description = null;
            this.releaseDate = movies.getResults()[i].getRelease_date();
            this.rating = movies.getResults()[i].getPopularity();
            this.originalLanguage = movies.getResults()[i].getOriginal_language();
            //movies.getResults()[i].getGenre_ids();
            this.genre = Genre.Education;

            Movie movie = new Movie(this.id, this.title, this.description, this.releaseDate, this.rating,
                    this.originalLanguage, this.genre);
            movieService.insertMovie(movie);

        }
        return movies;
    }

}
