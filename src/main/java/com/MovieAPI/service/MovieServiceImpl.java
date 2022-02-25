package com.MovieAPI.service;

import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import com.MovieAPI.repository.MovieRepository;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService  {

    @Autowired
    MovieRepository MovieRepository;

    public Iterable<Movie> listed() {
        return MovieRepository.findAll();
    }


    // do we return this?
    public Iterable<Movie> save(List<Movie> movies) {
        return MovieRepository.saveAll(movies);
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> Movies = new ArrayList<>();
        MovieRepository.findAll().forEach(Movies::add);
        return Movies;
    }

    @Override
    public Movie insertMovie(Movie Movie) {
        return MovieRepository.save(Movie);
    }

    @Override
    public Movie getMovieById(Long id) {
        return MovieRepository.findById(id).isPresent()?MovieRepository.findById(id).get():null;
    }

    //User Story 4 - Update Movie By Id Solution
    @Override
    public void updateMovieById(Long id, Movie Movie) {
        Movie retrievedMovie = MovieRepository.findById(id).get();

        retrievedMovie.setTitle(Movie.getTitle());
        retrievedMovie.setDescription(Movie.getDescription());
        retrievedMovie.setGenre(Movie.getGenre());

        MovieRepository.save(retrievedMovie);
    }

    @Override
    public void deleteMovieById(Long id) {
        MovieRepository.deleteById(id);
    }



    @Override
    public Set<Movie> getMovieByAttributes(String title, String description, String releaseDate,
                                            String rating, String originalLanguage, Genre genre) {

        System.out.println("init successful");
        Set<Movie> requestedMovies = new HashSet<>();
        ArrayList<Set<Movie>> movieIntersections = new ArrayList<>();

        if (title != null) {
            System.out.println("title query successful");
            Set<Movie> titleMovies = MovieRepository.findByTitle(title);
            movieIntersections.add(titleMovies);
        }
        if (description != null) {
            System.out.println("description unsuccessful");
            Set<Movie> titleMovies = MovieRepository.findByDescription(description);
            movieIntersections.add(titleMovies);
        }
        if (releaseDate != null) {

            Set<Movie> titleMovies = MovieRepository.findByReleaseDate(releaseDate);
            System.out.println("release unsuccessful: " + titleMovies);
            movieIntersections.add(titleMovies);
        }
        if (rating != null) {
            System.out.println("rating unsuccessful");
            Set<Movie> titleMovies = MovieRepository.findByRating(rating);
            movieIntersections.add(titleMovies);
        }
        if (originalLanguage != null) {
            System.out.println("language unsuccessful");
            Set<Movie> titleMovies = MovieRepository.findByOriginalLanguage(originalLanguage);
            movieIntersections.add(titleMovies);
        }
        if (genre != null) {
            System.out.println("genre unsuccessful");
            Set<Movie> titleMovies = MovieRepository.findByGenre(genre);
            movieIntersections.add(titleMovies);
        }

        System.out.println("all ifs completed");
        Set<Movie> previousMovie = movieIntersections.get(0);


        for (Set<Movie> movieList: movieIntersections) {
            System.out.println("Old previous movie " + previousMovie);
            requestedMovies = Sets.intersection(previousMovie, movieList);
            System.out.println("Requested movies: " + requestedMovies);
            previousMovie = requestedMovies;
        }
        System.out.println("all done");

        //requestedMovies = MovieRepository.findByTitleAndGenre(title, genre);


        return requestedMovies;
    }



}
