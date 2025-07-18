package com.example.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserNotAuthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
