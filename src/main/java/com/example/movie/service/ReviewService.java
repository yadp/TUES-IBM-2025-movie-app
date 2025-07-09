package com.example.movie.service;

import com.example.movie.model.User;
//import com.example.movie.model.Media;
import com.example.movie.model.Review;
import com.example.movie.repository.UserRepository;
//import com.example.movie.repository.MediaRepository;
import com.example.movie.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private UserRepository userRepo;

    //@Autowired
    //private MediaRepository mediaRepo;

    public List<Review> getReviewsOfUser(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }

        return reviewRepo.findByUserOrderByDateDesc(user);
    }

//    public List<Review> getReviewsOfMedia(String mediaName) {
//        Media media = mediaRepo.findByUsername(mediaName);
//        if (media == null) { throw new RuntimeException("Media not found: " + mediaName); }
//
//        return reviewRepo.findByMediaOrderByDateDesc(media);
//    }

    public void createReview(String username, String mediaName, String reviewContents) {
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }
        //Media media =  mediaRepo.findByTitle(mediaName);
        //if (media == null) { throw new RuntimeException("Media not found: " + mediaName); }

        reviewRepo.save(new Review(reviewContents, new User("", "", "", ""), 123, LocalDate.now())); //Boilerplate because media table doesn't exist yet
        //reviewRepo.save(new Review(reviewContents, user, media, LocalDate.now()));

    }

    public void deleteReview(String username, String mediaName) {
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }
        //Media media =  mediaRepo.findByTitle(mediaName);
        //if (media == null) { throw new RuntimeException("Media not found: " + mediaName); }

        //reviewRepo.deleteByUserAndMedia(user, media);
    }

}
