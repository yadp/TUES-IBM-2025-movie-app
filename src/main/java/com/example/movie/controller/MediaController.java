package com.example.movie.controller;

import com.example.movie.model.Media;
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

    @PatchMapping("/edit")
    public void editMedia(@RequestBody String title) {
        mediaService.editMedia(title);
    }
}
