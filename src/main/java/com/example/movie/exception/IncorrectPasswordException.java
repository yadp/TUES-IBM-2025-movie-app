package com.example.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class IncorrectPasswordException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
