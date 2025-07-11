package com.example.movie.service;

import com.example.movie.exception.UserAlreadyLoggedOutException;
import com.example.movie.model.User;
import com.example.movie.repository.UserRepository;
import com.example.movie.model.Media;
import com.example.movie.repository.MediaRepository;
import com.example.movie.model.Review;
import com.example.movie.repository.ReviewRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MediaRepository mediaRepo;

    @Autowired
    private HttpSession session;

    public List<Review> getReviewsOfUser() {
        String username = Optional.of((String) session.getAttribute("username")).orElseThrow(() ->
                new UserAlreadyLoggedOutException("User logged out")
        );
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }

        return reviewRepo.findByUserOrderByDateDesc(user);
    }

    public List<Review> getReviewsOfMedia(String title) {
        Media media = mediaRepo.findByTitle(title);
        if (media == null) { throw new RuntimeException("Media not found: " + title); }

        return reviewRepo.findByMediaOrderByDateDesc(media);
    }

    public void createReview(String title, String reviewContents) {
        String username = Optional.of((String) session.getAttribute("username")).orElseThrow(() ->
                new UserAlreadyLoggedOutException("User logged out")
        );
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }
        Media media =  mediaRepo.findByTitle(title);
        if (media == null) { throw new RuntimeException("Media not found: " + title); }

        reviewRepo.save(new Review(reviewContents, user, media, LocalDate.now()));

    }

    @Transactional
    public void deleteReview(String title) {
        String username = Optional.of((String) session.getAttribute("username")).orElseThrow(() ->
                new UserAlreadyLoggedOutException("User logged out")
        );
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }
        Media media =  mediaRepo.findByTitle(title);
        if (media == null) { throw new RuntimeException("Media not found: " + title); }

        reviewRepo.deleteByUserAndMedia(user, media);
    }

}
