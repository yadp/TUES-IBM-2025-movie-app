package com.example.movie.service;

import com.example.movie.exception.UserAlreadyLoggedOutException;
import com.example.movie.model.Rating;
import com.example.movie.repository.RatingRepository;
import com.example.movie.model.User;
import com.example.movie.repository.UserRepository;
import com.example.movie.model.Media;
import com.example.movie.repository.MediaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MediaRepository mediaRepo;

    @Autowired
    private HttpSession session;

    public Optional<Rating> getRatingOfUser(String title) {
        String username = Optional.of((String) session.getAttribute("username")).orElseThrow(() ->
                new UserAlreadyLoggedOutException("User logged out")
        );
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }
        Media media = mediaRepo.findByTitle(title);
        if (media == null) { throw new RuntimeException("Media not found: " + title); }

        return ratingRepo.findByUserAndMedia(user, media);
    }

    public List<Rating> getRatingsOfMedia(String title) {
        Media media = mediaRepo.findByTitle(title);
        if (media == null) { throw new RuntimeException("Media not found: " + title); }

        return ratingRepo.findByMedia(media);
    }

    public void giveRating(String title, float rating) {
        String username = Optional.of((String) session.getAttribute("username")).orElseThrow(() ->
                new UserAlreadyLoggedOutException("User logged out")
        );
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }
        Media media =  mediaRepo.findByTitle(title);
        if (media == null) { throw new RuntimeException("Media not found: " + title); }

        ratingRepo.save(new Rating(user, media, rating));

    }

    public void changeRating(String title, float newRating) {
        String username = Optional.of((String) session.getAttribute("username")).orElseThrow(() ->
                new UserAlreadyLoggedOutException("User logged out")
        );
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }
        Media media =  mediaRepo.findByTitle(title);
        if (media == null) { throw new RuntimeException("Media not found: " + title); }

        Optional<Rating> optionalRating = ratingRepo.findByUserAndMedia(user, media);

        if (optionalRating.isPresent()) {
            Rating rating = optionalRating.get();
            rating.setRating(newRating);
            ratingRepo.save(rating);
        } else {
            // You can either throw or create a new one
            ratingRepo.save(new Rating(user, media, newRating));
        }
    }
}
