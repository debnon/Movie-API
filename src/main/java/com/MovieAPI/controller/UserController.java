package com.MovieAPI.controller;

import com.techreturners.bookmanager.exception.DuplicateIDException;
import com.techreturners.bookmanager.exception.GetEmptyException;
import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.service.BookManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class UserController {

    @Autowired
    BookManagerService bookManagerService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookManagerService.getAllBooks();
        if (books.isEmpty()) {
            throw new GetEmptyException("No books present in the database");
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping({"/{bookId}"})
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        Book book = bookManagerService.getBookById(bookId);
        //Return exception message when no book is present with given id
        if (book == null) {
            throw new GetEmptyException("There is no book present with that ID");
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book existingBook = bookManagerService.getBookById(book.getId());
        if (existingBook != null) {
            throw new DuplicateIDException("There is already a book with the given ID.. Please try with another ID");
        }

        Book newBook = bookManagerService.insertBook(book);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("book", "/api/v1/book/" + newBook.getId().toString());
        return new ResponseEntity<>(newBook, httpHeaders, HttpStatus.CREATED);
    }

    //User Story 4 - Update Book By Id Solution
    @PutMapping({"/{bookId}"})
    public ResponseEntity<Book> updateBookById(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        book = bookManagerService.getBookById(bookId);
        //Return exception message when user is trying to update a book that does not exist
        if (book == null) {
            throw new GetEmptyException("Book not found. Please try to update a book that exists.");
        }
        bookManagerService.updateBookById(bookId, book);
        return new ResponseEntity<>(bookManagerService.getBookById(bookId), HttpStatus.OK);
    }

    //delete book by id
    @DeleteMapping({"/{bookId}"})
    public ResponseEntity<Book> deleteBookById(@PathVariable("bookId") Long bookId) {
        Book book = bookManagerService.getBookById(bookId);
        //Return exception message when user is trying to delete a book that does not exist
        if (book == null) {
            throw new GetEmptyException("Book not found. Please try to delete a book that exists.");
        }
        bookManagerService.deleteBookById(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
