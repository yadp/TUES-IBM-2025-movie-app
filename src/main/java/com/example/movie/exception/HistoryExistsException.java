package com.example.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class HistoryExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HistoryExistsException(String message) {
        super(message);
    }
}
