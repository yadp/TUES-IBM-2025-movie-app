package com.example.movie.repository;

import com.example.movie.model.User;
//import com.example.movie.model.Media;
import com.example.movie.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserOrderByDateDesc(User user);
    //List<Review> findByMediaOrderByDateDesc(Media media);
    //void deleteByUserAndMedia(User user, Media media);
}


