package com.MovieAPI.service;

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

    @Override
    public ArrayList<Movie> getMovieByTitle(String title) {
        List moxy = new List;
        return MovieRepository.findByTitle(title).isPresent()?MovieRepository.findByTitle(title).get():null;
    }
//
//    @Override
//    public ArrayList<Movie> getMovieByAttributes(ArrayList<String> attributes) {
//        //
//    }



}
