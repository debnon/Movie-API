package com.MovieAPI.controller;

import com.MovieAPI.constants.Constants;
import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import com.MovieAPI.responsemodel.Results;
import com.MovieAPI.service.MovieService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.IOException;
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
    List<Genre> genres;
    String poster;
    String backdrop;
    Long runtime;
    String status;
    String imdbID;

    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    public void getMoviesFromTmdb() throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader("src/main/resources/json/fixedJson.json"));
        ArrayList<String> movieIDs = new ArrayList<>();

        for (Object o : a)
        {
            JSONObject movie = (JSONObject) o;
            String id = String.valueOf(movie.get("id"));
            movieIDs.add(id);
        }

        RestTemplate restTemplate = new RestTemplate();
        for (String id : movieIDs) {
            String url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + Constants.TMDB_API_KEY;

            Results results = restTemplate.getForObject(url, Results.class);

            this.id = results.getId();
            this.title = results.getOriginal_title();
            this.description = results.getOverview();
            this.releaseDate = results.getRelease_date();
            this.rating = results.getPopularity();
            this.originalLanguage = results.getOriginal_language();

            genres = new ArrayList<>();
            for (com.MovieAPI.responsemodel.Genre entry : results.getGenres()) {
                genres.add(Genre.genreMapper.get((entry.getId())));
            }

            this.poster = results.getPoster_path();
            this.backdrop = results.getBackdrop_path();
            this.runtime = results.getRuntime();
            this.status = results.getStatus();
            this.imdbID = results.getImdbID();

            Movie movie = new Movie(this.id, this.title, this.description, this.releaseDate, this.rating,
                    this.originalLanguage, this.genres, this.poster, this.backdrop, this.runtime, this.status,
                    this.imdbID);
            movieService.insertMovie(movie);
        }

    }

}
