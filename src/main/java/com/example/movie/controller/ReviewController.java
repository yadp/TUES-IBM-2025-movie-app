package com.example.movie.controller;

import com.example.movie.exception.ReviewExistsException;
import com.example.movie.exception.ReviewNotFoundException;
import com.example.movie.exception.UserNotFoundException;
//import com.example.movie.exception.MediaNotFoundException;
import com.example.movie.model.Review;
import com.example.movie.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/by-user")
    public List<Review> reviewsByUser(@RequestBody String username) throws UserNotFoundException {
        return reviewService.getReviewsOfUser(username);
    }

//    @GetMapping("/by-media")
//    public List<Review> reviewsByMedia(@RequestBody String mediaName) throws MediaNotFoundException {
//        return reviewService.getReviewsOfMedia(mediaName);
//    }

    @PostMapping("/add-review")
    public void addReview(@RequestBody String username, String mediaName, String reviewContents) throws ReviewExistsException {
        reviewService.createReview(username, mediaName, reviewContents);
    }

    @DeleteMapping("delete-review")
    public void deleteReview(@RequestBody String username, String mediaName) throws ReviewNotFoundException {
        reviewService.deleteReview(username, mediaName);
    }
}

