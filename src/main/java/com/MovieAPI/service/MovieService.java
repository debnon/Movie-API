package com.MovieAPI.service;

import com.MovieAPI.model.Movie;
import com.techreturners.bookmanager.model.Book;

import java.util.List;

public interface MovieService {

    List<Movie> getAllBooks();
    Book insertBook(Book book);
    Book getBookById(Long id);

    //User Story 4 - Update Book By Id Solution
    void updateBookById(Long id, Book book);

    void deleteBookById(Long id);
}


