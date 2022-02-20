package com.MovieAPI.exception;

public class GetEmptyException extends RuntimeException {

    public GetEmptyException(String message) {
        super(message);
    }

    public GetEmptyException(Throwable cause) {
        super(cause);
    }
}
