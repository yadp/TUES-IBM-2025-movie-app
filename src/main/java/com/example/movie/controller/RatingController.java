package com.example.movie.controller;

import com.example.movie.DTO.RatingDTO;
import com.example.movie.exception.MediaNotFoundException;
import com.example.movie.exception.RatingExistsException;
import com.example.movie.exception.UserNotFoundException;
import com.example.movie.model.Rating;
import com.example.movie.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/single-of-user")
    public Optional<Rating> getUserRating(@RequestBody String title)
    throws UserNotFoundException, MediaNotFoundException {
        return ratingService.getRatingOfUser(title);
    }

    @GetMapping("/many-of-media")
    public List<Rating> ratingsOfMedia(@RequestBody String title) throws MediaNotFoundException {
        return ratingService.getRatingsOfMedia(title);
    }

    @PostMapping("/add")
    public void addRating(@RequestBody RatingDTO ratingDTO)
    throws UserNotFoundException, MediaNotFoundException, RatingExistsException {
        ratingService.giveRating(ratingDTO.getTitle(), ratingDTO.getRating());
    }

    @PatchMapping("/change")
    public void changeRating(@RequestBody RatingDTO ratingDTO)
    throws UserNotFoundException, MediaNotFoundException, RatingExistsException {
        ratingService.changeRating(ratingDTO.getTitle(), ratingDTO.getRating());
    }
}

