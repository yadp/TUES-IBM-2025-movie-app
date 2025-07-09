package com.example.movie.controller;

import com.example.movie.model.Media;
import com.example.movie.model.Movie;
import com.example.movie.model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.movie.service.MediaService;

public class MediaController {
    @Autowired
    MediaService mediaService;

    @PostMapping("/delete")
    public void deleteMedia(@RequestBody String title) {
        mediaService.deleteMedia(title);
    }

    @PatchMapping("/edit/m")
    public void editMovie(@RequestBody Movie movie) {
        mediaService.editMovie(movie);
    }

    @PatchMapping("/edit/s")
    public void editShow(@RequestBody Show show) {
        mediaService.editShow(show);
    }

    @PostMapping("/create/m")
    public void createMovie(@RequestBody Movie movie) {
        mediaService.createMovie(movie);
    }

    @PostMapping("/create/s")
    public void createShow(@RequestBody Show show) {
        mediaService.createShow(show);
    }
}
