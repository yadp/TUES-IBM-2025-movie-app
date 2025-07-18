package com.example.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HistoryNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HistoryNotFoundException(String message) {
        super(message);
    }
}
