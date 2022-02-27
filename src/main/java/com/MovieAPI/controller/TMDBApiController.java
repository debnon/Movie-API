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

//    @GetMapping("movies/ids")
//    public List<String> getMovieIDsFromTMDB(String americanDate) {
//
//        String url = "http://files.tmdb.org/p/exports/movie_ids_" + americanDate + ".json.gz";
//
//    }

    @GetMapping("/movies")
    public void getMoviesFromTmdb() throws IOException, ParseException {
//        String url = "https://api.themoviedb.org/3/discover/movie?api_key="
//                + Constants.TMDB_API_KEY +
//                "&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&" +
//                "page=1&with_watch_monetization_types=flatrate";

        JSONParser parser = new JSONParser();
        System.out.println("First point");
        JSONArray a = (JSONArray) parser.parse(new FileReader("src/main/resources/json/fixedJson.json"));
        System.out.println(a);
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

            // Results results = new Results();

//            System.out.println(movie.getId());
//            System.out.println(movies.()[i].getOriginal_title());
//            System.out.println(movies.()[i].getRelease_date());
//            System.out.println(movies.()[i].getPopularity());
//            System.out.println(movies.()[i].getOriginal_language());
//            System.out.println(movies.()[i].getGenre_ids());

            this.id = results.getId();
            this.title = results.getOriginal_title();
            //this.description = movies.getResults()[i].getOverview();
            this.description = null;
            this.releaseDate = results.getRelease_date();
            this.rating = results.getPopularity();
            this.originalLanguage = results.getOriginal_language();
            //movies.getResults()[i].getGenre_ids();
            this.genre = Genre.Education;

            Movie movie = new Movie(this.id, this.title, this.description, this.releaseDate, this.rating,
                    this.originalLanguage, this.genre);
            movieService.insertMovie(movie);
        }

    }

}
