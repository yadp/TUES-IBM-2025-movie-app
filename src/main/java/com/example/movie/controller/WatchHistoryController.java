package com.example.movie.controller;

import java.util.List;

import com.example.movie.exception.HistoryExistsException;
import com.example.movie.exception.HistoryNotFoundException;
import com.example.movie.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.movie.model.WatchHistory;
import com.example.movie.service.WatchHistoryService;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/watch-history")
public class WatchHistoryController {

    @Autowired
    private WatchHistoryService watchHistoryService;

    @GetMapping("/")
    public List<WatchHistory> getUserHistory() throws UserNotFoundException {
        return watchHistoryService.getWatchHistory();
    }

    @PostMapping("/add")
    public void addToWatchHistory(@RequestBody String title) throws HistoryExistsException {
        watchHistoryService.createWatchHistory(title);
    }

    @DeleteMapping("/delete")
    public void deleteFromWatchHistory(@RequestBody String title) throws HistoryNotFoundException {
        watchHistoryService.deleteWatchHistory(title);
    }
}

