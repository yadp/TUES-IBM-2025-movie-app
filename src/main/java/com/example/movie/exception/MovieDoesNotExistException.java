package com.example.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MovieDoesNotExistException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public MovieDoesNotExistException(String message) {
        super(message);
    }
}

