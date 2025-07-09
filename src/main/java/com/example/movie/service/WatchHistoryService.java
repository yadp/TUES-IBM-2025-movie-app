package com.example.movie.service;


import com.example.movie.model.WatchHistory;
import com.example.movie.repository.WatchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movie.exception.*;
import com.example.movie.model.User;
import com.example.movie.repository.UserRepository;
//import com.example.movie.model.Media;
//import com.example.movie.repository.MediaRepository;

import javax.print.attribute.standard.Media;
import java.time.LocalDate;


import java.util.List;

@Service
public class WatchHistoryService {

    @Autowired
    private WatchHistoryRepository watchHistoryRepo;

    @Autowired
    private UserRepository userRepo;

    //@Autowired
    //private MediaRepository mediaRepo;

    public List<WatchHistory> getWatchHistory(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }

        return watchHistoryRepo.findByUserOrderByDateDesc(user);
    }

    public void createWatchHistory(String username, String mediaName) {
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }
        //Media media =  mediaRepo.findByTitle(mediaName);
        //if (media == null) { throw new RuntimeException("Media not found: " + mediaName); }

        watchHistoryRepo.save(new WatchHistory(new User("", "", "", ""), 123, LocalDate.now())); //Boilerplate because media table doesn't exist yet
        //watchHistoryRepo.save(new WatchHistory(user, media, LocalDate.now()));
    }

    public void deleteWatchHistory(String username, String mediaName) {
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new RuntimeException("User not found: " + username); }
        //Media media =  mediaRepo.findByTitle(mediaName);
        //if (media == null) { throw new RuntimeException("Media not found: " + mediaName); }

        //watchHistoryRepo.deleteByUserAndMedia(user, media);
    }

}
