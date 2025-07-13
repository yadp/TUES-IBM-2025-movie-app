package com.example.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<Map<String, String>> handleIncorrectPassword(IncorrectPasswordException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MovieDoesNotExistException.class)
    public ResponseEntity<Map<String, String>> handleMovieNotFound(MovieDoesNotExistException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovieExistsException.class)
    public ResponseEntity<Map<String, String>> handleMovieExists(MovieExistsException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(ShowDoesNotExistException.class)
    public ResponseEntity<Map<String, String>> handleShowNotFound(ShowDoesNotExistException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShowExistsException.class)
    public ResponseEntity<Map<String, String>> handleShowExists(ShowExistsException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(UserAlreadyLoggedInException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyLoggedIn(UserAlreadyLoggedInException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(UserAlreadyLoggedOutException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyLoggedOut(UserAlreadyLoggedOutException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserExists(UserExistsException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    public ResponseEntity<Map<String, String>> handleUserNotAuthorized(UserNotAuthorizedException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Optional generic fallback handler for any other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllOtherExceptions(Exception ex) {
        ex.printStackTrace(); // for debugging/logging
        return buildResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Helper method to build consistent JSON response
    private ResponseEntity<Map<String, String>> buildResponse(String message, HttpStatus status) {
        Map<String, String> body = new HashMap<>();
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
}
