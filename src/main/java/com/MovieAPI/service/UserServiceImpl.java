package com.MovieAPI.service;

import com.techreturners.Moviemanager.model.Movie;
import com.techreturners.Moviemanager.repository.MovieManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    MovieManagerRepository MovieManagerRepository;

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> Movies = new ArrayList<>();
        MovieManagerRepository.findAll().forEach(Movies::add);
        return Movies;
    }

    @Override
    public Movie insertMovie(Movie Movie) {
        return MovieManagerRepository.save(Movie);
    }

    @Override
    public Movie getMovieById(Long id) {
        return MovieManagerRepository.findById(id).isPresent()?MovieManagerRepository.findById(id).get():null;
    }

    //User Story 4 - Update Movie By Id Solution
    @Override
    public void updateMovieById(Long id, Movie Movie) {
        Movie retrievedMovie = MovieManagerRepository.findById(id).get();

        retrievedMovie.setTitle(Movie.getTitle());
        retrievedMovie.setDescription(Movie.getDescription());
        retrievedMovie.setAuthor(Movie.getAuthor());
        retrievedMovie.setGenre(Movie.getGenre());

        MovieManagerRepository.save(retrievedMovie);
    }

    @Override
    public void deleteMovieById(Long id) {
        MovieManagerRepository.deleteById(id);
    }

}
