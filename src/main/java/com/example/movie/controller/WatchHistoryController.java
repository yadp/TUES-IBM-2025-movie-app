package com.example.movie.controller;

import java.util.List;

import com.example.movie.exception.HistoryExistsException;
import com.example.movie.exception.HistoryNotFoundException;
import com.example.movie.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.movie.model.WatchHistory;
import com.example.movie.service.WatchHistoryService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/watch-history")
public class WatchHistoryController {

    @Autowired
    private WatchHistoryService watchHistoryService;

    @GetMapping("/")
    public List<WatchHistory> getUserById(@RequestBody String username) throws UserNotFoundException {
        return watchHistoryService.getWatchHistory(username);
    }

    @PostMapping("/add-media")
    public void addToWatchHistory(@RequestBody String username, String mediaName) throws HistoryExistsException {
        watchHistoryService.createWatchHistory(username, mediaName);
    }

    @DeleteMapping("delete-media")
    public void deleteFromWatchHistory(@RequestBody String username, String mediaName) throws HistoryNotFoundException {
        watchHistoryService.deleteWatchHistory(username, mediaName);
    }
}

