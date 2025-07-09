package com.example.movie.service;

import com.example.movie.model.Rating;
import com.example.movie.repository.RatingRepository;
import com.example.movie.model.User;
import com.example.movie.repository.UserRepository;
//import com.example.movie.model.Media;
//import com.example.movie.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepo;

    @Autowired
    private UserRepository userRepo;

    //@Autowired
    //private MediaRepository mediaRepo;

//    public Rating getRatingOfUser(String username, String mediaName) {
//        User user = userRepo.findByUsername(username);
//        if (user == null) { throw new RuntimeException("User not found: " + username); }
//        Media media = mediaRepo.findByUsername(mediaName);
//        if (media == null) { throw new RuntimeException("Media not found: " + mediaName); }
//
//        return ratingRepo.findByUserAndMedia(user, media);
//    }

//    public List<Rating> getRatingsOfMedia(String mediaName) {
//        Media media = mediaRepo.findByUsername(mediaName);
//        if (media == null) { throw new RuntimeException("Media not found: " + mediaName); }
//
//        return ratingRepo.findByMedia(media);
//    }

    public void giveRating(String username, String mediaName, float rating) {
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }
        //Media media =  mediaRepo.findByTitle(mediaName);
        //if (media == null) { throw new RuntimeException("Media not found: " + mediaName); }

        ratingRepo.save(new Rating(new User("", "", "", ""), 123, rating)); //Boilerplate because media table doesn't exist yet
        //reviewRepo.save(new Rating(user, media, rating));

    }

    public void changeRating(String username, String mediaName, float newRating) {
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }
        //Media media =  mediaRepo.findByTitle(mediaName);
        //if (media == null) { throw new RuntimeException("Media not found: " + mediaName); }

        ratingRepo.save(new Rating(new User("", "", "", ""), 123, newRating)); //Boilerplate because media table doesn't exist yet
        //reviewRepo.save(new Rating(user, media, newRating));
    }
}
