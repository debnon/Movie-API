package com.MovieAPI.controller;

import com.MovieAPI.constants.Constants;
import com.MovieAPI.model.GenreNew;
import com.MovieAPI.model.Movie;
import com.MovieAPI.responsemodel.Movies;
import com.MovieAPI.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tmdb")
public class TMDBApiController {

    Long id;
    String title;
    String description;
    String releaseDate;
    String rating;
    String originalLanguage;
    GenreNew genre;
    String poster;

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
        System.out.println(movies.getTotal_pages());
        System.out.println(movies.getResults().length);
        // for(int j=1; j<=movies.getTotal_pages();j++) {
        for (int i = 0; i <= movies.getResults().length - 1; i++) {
            this.id = movies.getResults()[i].getId();
            this.title = movies.getResults()[i].getOriginal_title();
            this.description = movies.getResults()[i].getOverview();
            this.releaseDate = movies.getResults()[i].getRelease_date();
            this.rating = String.valueOf(movies.getResults()[i].getVote_average());
            this.originalLanguage = movies.getResults()[i].getOriginal_language();
            int genreSize = movies.getResults()[i].getGenre_ids().length;

            Movie movie = new Movie();
            List genreList = new ArrayList();
            for (int k = 0; k <= genreSize - 1; k++) {
                int genreId = movies.getResults()[i].getGenre_ids()[k];
                this.genre = GenreNew.getGenreById(genreId);
                genreList.add(this.genre);
            }
            this.poster = movies.getResults()[i].getPoster_path();

            movie.setId(this.id);
            movie.setTitle(this.title);
            movie.setDescription(this.description);
            movie.setReleaseDate(this.releaseDate);
            movie.setRating(this.rating);
            movie.setOriginalLanguage(this.originalLanguage);
            movie.setGenre(genreList);
            movie.setPoster(this.poster);

            movieService.insertMovie(movie);
        }
        //}
        return movies;
    }

}
