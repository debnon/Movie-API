package com.MovieAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class MovieExceptionHandler {

    // If more exceptions are being handled here they can be appended to "value = ..."
    @ExceptionHandler(value = {GetEmptyException.class})
    public ResponseEntity<Object> handleGetEmptyException(GetEmptyException e) {
        CustomException getException = new CustomException(
                ZonedDateTime.now(),
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );

        return new ResponseEntity<>(getException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DuplicateIDException.class})
    public ResponseEntity<Object> handleDuplicateIDException(DuplicateIDException e) {
        CustomException getException = new CustomException(
                ZonedDateTime.now(),
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );

        return new ResponseEntity<>(getException, HttpStatus.BAD_REQUEST);
    }

}
