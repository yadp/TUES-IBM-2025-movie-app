package com.example.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ShowExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ShowExistsException(String message) {
        super(message);
    }
}
