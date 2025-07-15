package com.example.movie.controller;

import com.example.movie.DTO.ReviewDTO;
import com.example.movie.exception.MediaNotFoundException;
import com.example.movie.exception.ReviewExistsException;
import com.example.movie.exception.ReviewNotFoundException;
import com.example.movie.exception.UserNotFoundException;
//import com.example.movie.exception.MediaNotFoundException;
import com.example.movie.model.Review;
import com.example.movie.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/of-user")
    public List<Review> reviewsOfUser() throws UserNotFoundException {
        return reviewService.getReviewsOfUser();
    }

    @PostMapping("/of-media")
    public List<Review> reviewsOfMedia(@RequestBody String title) throws MediaNotFoundException {
        return reviewService.getReviewsOfMedia(title);
    }

    @PostMapping("/add")
    public void addReview(@RequestBody ReviewDTO reviewDTO) throws ReviewExistsException {
        reviewService.createReview(reviewDTO.getTitle(), reviewDTO.getContents(), reviewDTO.getRating());
    }


    @DeleteMapping("/delete")
    public void deleteReview(@RequestBody String title) throws ReviewNotFoundException {
        reviewService.deleteReview(title);
    }
}

