package com.example.movie.controller;

import com.example.movie.model.Media;
import com.example.movie.model.Movie;
import com.example.movie.model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.movie.service.MediaService;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/media")
public class MediaController {
    @Autowired
    MediaService mediaService;

    @DeleteMapping("/delete")
    public void deleteMedia(@RequestBody String title) {
        title = title.replace("\"", "");
        mediaService.deleteMedia(title);
    }
    @PatchMapping("/edit/movie")
    public void editMovie(@RequestBody Movie movie) {
        mediaService.editMovie(movie);
    }

    @PatchMapping("/edit/show")
    public void editShow(@RequestBody Show show) {
        mediaService.editShow(show);
    }

    @PostMapping("/create/movie")
    public void createMovie(@RequestBody Movie movie) {
        mediaService.createMovie(movie);
    }

    @PostMapping("/create/show")
    public void createShow(@RequestBody Show show) {
        mediaService.createShow(show);
    }

    @GetMapping("/all")
    public List<Media> getAllMedia() {
        return mediaService.getAllMedia();  // or whatever your service method is
    }
}
