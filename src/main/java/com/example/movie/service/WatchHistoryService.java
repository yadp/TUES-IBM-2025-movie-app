package com.example.movie.service;


import com.example.movie.exception.UserAlreadyLoggedOutException;
import com.example.movie.model.WatchHistory;
import com.example.movie.repository.WatchHistoryRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movie.model.User;
import com.example.movie.repository.UserRepository;
import com.example.movie.model.Media;
import com.example.movie.repository.MediaRepository;

import java.time.LocalDate;


import java.util.List;
import java.util.Optional;

@Service
public class WatchHistoryService {

    @Autowired
    private WatchHistoryRepository watchHistoryRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MediaRepository mediaRepo;

    @Autowired
    private HttpSession session;

    public List<WatchHistory> getWatchHistory() {
        String username = Optional.of((String) session.getAttribute("username")).orElseThrow(() ->
                new UserAlreadyLoggedOutException("User logged out")
        );
        User user = userRepo.findByUsername(username);
        if (user == null) {
            System.out.println(username);
            throw new RuntimeException("User not found: " + username); }

        return watchHistoryRepo.findByUserOrderByDateDesc(user);
    }

    public void createWatchHistory(String title) {
        String username = Optional.of((String) session.getAttribute("username")).orElseThrow(() ->
                new UserAlreadyLoggedOutException("User logged out")
        );
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }
        Media media =  mediaRepo.findByTitle(title);
        if (media == null) { throw new RuntimeException("Media not found: " + title); }

        watchHistoryRepo.save(new WatchHistory(user, media, LocalDate.now()));
    }

    @Transactional
    public void deleteWatchHistory(String title) {
        String username = Optional.of((String) session.getAttribute("username")).orElseThrow(() ->
                new UserAlreadyLoggedOutException("User logged out")
        );
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }
        Media media =  mediaRepo.findByTitle(title);
        if (media == null) { throw new RuntimeException("Media not found: " + title); }

        watchHistoryRepo.deleteByUserAndMedia(user, media);
    }

}
