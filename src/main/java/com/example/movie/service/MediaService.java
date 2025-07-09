package com.example.movie.service;

import com.example.movie.exception.*;
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

    public void checkAdmin() {
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
    }

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

    public void editMovie(Movie movie) {
        checkAdmin();

        Movie editedMovie = (Movie) mediaRepo.findByTitle(movie.getTitle());

        if(mediaRepo.findByTitle(movie.getTitle()) == null) {
            throw new MovieDoesNotExistException("Movie not in db");
        }

        editedMovie.setDuration(movie.getDuration());
        editedMovie.setDescription(movie.getDescription());
        editedMovie.setGenre(movie.getGenre());
        editedMovie.setYear(movie.getYear());
        editedMovie.setType(movie.getType());
        editedMovie.setAverageRating(movie.getAverageRating());
        editedMovie.setRatingsCount(movie.getRatingsCount());

        mediaRepo.save(editedMovie);
    }

    public void editShow(Show show) {
        checkAdmin();

        Show editedShow = (Show) mediaRepo.findByTitle(show.getTitle());

        if(mediaRepo.findByTitle(show.getTitle()) == null) {
            throw new ShowDoesNotExistException("Show not in db");
        }

        editedShow.setGenre(show.getGenre());
        editedShow.setYear(show.getYear());
        editedShow.setDescription(show.getDescription());
        editedShow.setType(show.getType());
        editedShow.setAverageRating(show.getAverageRating());
        editedShow.setRatingsCount(show.getRatingsCount());
        editedShow.setNumber_of_episodes(show.getNumber_of_episodes());
        editedShow.setNumber_of_seasons(show.getNumber_of_seasons());

        mediaRepo.save(editedShow);
    }

    public void createMovie(Movie movie) {
        if(mediaRepo.findByTitle(movie.getTitle()) != null) {
            throw new MovieExistsException("Movie already in db");
        }

        mediaRepo.save(movie);
    }

    public void createShow(Show show) {
        if(mediaRepo.findByTitle(show.getTitle()) != null) {
            throw new ShowExistsException("Show already in db");
        }

        mediaRepo.save(show);
    }
}
