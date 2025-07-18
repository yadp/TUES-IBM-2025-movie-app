package com.example.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserAlreadyLoggedOutException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UserAlreadyLoggedOutException(String message) {
        super(message);
    }
}
