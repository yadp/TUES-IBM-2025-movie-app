package com.example.movie.service;

import com.example.movie.exception.UserAlreadyLoggedOutException;
import com.example.movie.exception.UserNotAuthorizedException;
import com.example.movie.exception.UserNotFoundException;
import com.example.movie.model.Media;
import com.example.movie.model.Movie;
import com.example.movie.model.Show;
import com.example.movie.repository.MediaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.movie.model.User;
import com.example.movie.repository.UserRepository;

public class MediaService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MediaRepository mediaRepo;

    @Autowired
    private HttpSession session;

    public void deleteMedia(String title) {
        if(session.getAttribute("username") == null) {
            throw new UserAlreadyLoggedOutException("User logged out");
        }

        User found = userRepo.findByUsername(String.valueOf(session.getAttribute("username")));

        if(found == null) {
            throw new UserNotFoundException("No such user");
        }

        if(!found.getType().equals("admin")) {
            throw new UserNotAuthorizedException("Not and admin");
        }

        mediaRepo.delete(mediaRepo.findByTitle(title));
    }

    public void editMedia(String title) {
        if(session.getAttribute("username") == null) {
            throw new UserAlreadyLoggedOutException("User logged out");
        }

        User found = userRepo.findByUsername(String.valueOf(session.getAttribute("username")));

        if(found == null) {
            throw new UserNotFoundException("No such user");
        }

        if(!found.getType().equals("admin")) {
            throw new UserNotAuthorizedException("Not and admin");
        }

        if(mediaRepo.findByTitle(title).getType().equals("movie")) {
            Movie newMovie = (Movie) mediaRepo.findByTitle(title);


        }
        else {

        }
    }

    public void createMovie(Movie movie) {
        if(mediaRepo.findByTitle(movie.getTitle()) != null) {

        }
    }

    public void createShow(Show show) {

    }
}
