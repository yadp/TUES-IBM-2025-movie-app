package com.example.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class ReviewExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ReviewExistsException(String message) {
        super(message);
    }
}
