package com.MovieAPI.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class CustomException {
    private ZonedDateTime timeStamp;
    private HttpStatus HttpStatus;
    private String message;

    public CustomException(ZonedDateTime timeStamp,
                           HttpStatus httpStatus, String message) {
        this.timeStamp = timeStamp;
        //this.throwable = throwable;
        HttpStatus = httpStatus;
        this.message = message;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public org.springframework.http.HttpStatus getHttpStatus() {
        return HttpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
