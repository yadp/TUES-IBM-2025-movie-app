package com.example.movie.repository;

import com.example.movie.model.User;
import com.example.movie.model.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Long> {
    List<WatchHistory> findByUser(User user);
    //void deleteByUserAndMedia(User user, Media media);
}


