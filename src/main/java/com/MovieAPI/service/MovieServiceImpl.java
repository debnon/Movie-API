package com.MovieAPI.service;

import com.MovieAPI.model.Genre;
import com.MovieAPI.model.Movie;
import com.MovieAPI.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService  {

    @Autowired
    MovieRepository MovieRepository;

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

//    @Override
//    public ArrayList<Movie> getMovieByTitle(String title) {
//        MovieRepository.findByName("yo");
//        return MovieRepository.findByTitle(title).isPresent()?MovieRepository.findByTitle(title).get():null;
//    }

    @Override
    public List<Movie> getMovieByAttributes(String title, Genre genre) {

        List<Movie> requestedMovies = new ArrayList<>();
        if (title != null) {

            requestedMovies = MovieRepository.findByTitle(title);
            System.out.println("requestedMovies: " + requestedMovies);
        }

        List<Movie> helperMovies1 = new ArrayList<>();
        if (genre != null) {
            helperMovies1 = MovieRepository.findByGenre(genre);
            System.out.println("helper1: " + helperMovies1);
        }

        List<Movie> helperMovies2 = new ArrayList<>();
        for (Movie movie : requestedMovies) {
            System.out.println("movie: " + movie);
            if (helperMovies1.contains(movie)) {
                helperMovies2.add(movie);
            }
        }

        return helperMovies1;
    }



}
