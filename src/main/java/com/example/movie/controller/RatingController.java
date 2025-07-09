package com.example.movie.controller;

import com.example.movie.exception.RatingExistsException;
import com.example.movie.exception.UserNotFoundException;
import com.example.movie.model.Rating;
import com.example.movie.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

//    @GetMapping("/of-specific-user")
//    public Rating getUserRating(@RequestBody String username, String mediaName)
//    throws UserNotFoundException, MediaNotFoundException {
//        return ratingService.getRatingOfUser(username, mediaName);
//    }

//    @GetMapping("/many-of-media")
//    public List<Rating> ratingsOfMedia(@RequestBody String mediaName) throws MediaNotFoundException {
//        return ratingService.getRatingsOfMedia(mediaName);
//    }

//    @PostMapping("/add-rating")
//    public void addRating(@RequestBody String username, String mediaName, float rating)
//    throws UserNotFoundException, MediaNotFoundException, RatingExistsException {
//        ratingService.giveRating(username, mediaName, rating);
//    }

//    @PatchMapping("/change-rating")
//    public void changeRating(@RequestBody String username, String mediaName, float newRating)
//    throws UserNotFoundException, MediaNotFoundException, RatingExistsException {
//        ratingService.changeRating(username, mediaName, newRating);
//    }
}

