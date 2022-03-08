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

    List<Movie> curatedMovies = new ArrayList<>();

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
        retrievedMovie.setGenre(Movie.getGenres());

        MovieRepository.save(retrievedMovie);
    }

    @Override
    public void deleteMovieById(Long id) {
        MovieRepository.deleteById(id);
    }



    @Override
    public Set<Movie> getMovieByAttributes(String title, String description, String releaseDate,
                                           String rating, String originalLanguage, List<Genre> genres) {

        Set<Movie> requestedMovies = new HashSet<>();
        ArrayList<Set<Movie>> movieIntersections = new ArrayList<>();

        if (title != null) {
            Set<Movie> titleMovies = MovieRepository.findByTitle(title);
            movieIntersections.add(titleMovies);
        }

        if (description != null) {
            Set<Movie> titleMovies = MovieRepository.findByDescription(description);
            movieIntersections.add(titleMovies);
        }

        if (releaseDate != null) {
            Set<Movie> titleMovies = MovieRepository.findByReleaseDate(releaseDate);
            movieIntersections.add(titleMovies);
        }

        if (rating != null) {
            Set<Movie> titleMovies = MovieRepository.findByRating(rating);
            movieIntersections.add(titleMovies);
        }

        if (originalLanguage != null) {
            Set<Movie> titleMovies = MovieRepository.findByOriginalLanguage(originalLanguage);
            movieIntersections.add(titleMovies);
        }

        if (genres != null) {
            Set<Movie> titleMovies = MovieRepository.findByGenresIn(genres);
            movieIntersections.add(titleMovies);
        }

        Set<Movie> previousMovie = movieIntersections.get(0);

        for (Set<Movie> movieList: movieIntersections) {
            requestedMovies = Sets.intersection(previousMovie, movieList);
            previousMovie = requestedMovies;
        }

        return requestedMovies;
    }

    @Override
    public List<Movie> getCuratedMovies() {
        return curatedMovies;
    }

    @Override
    public Movie addCuratedMovieByID(Long id) {
        Movie curatedMovie = getMovieById(id);
        curatedMovies.add(curatedMovie);
        return curatedMovie;
    }

    @Override
    public List<Movie> addCuratedMoviesByIDs(List<String> IDs) {
        for (String id : IDs) {
            Movie curatedMovie = getMovieById(Long.valueOf(id));
            curatedMovies.add(curatedMovie);
        }

        return curatedMovies;
    }



}
