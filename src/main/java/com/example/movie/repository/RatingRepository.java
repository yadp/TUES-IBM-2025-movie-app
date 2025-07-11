package com.example.movie.repository;

import com.example.movie.model.Rating;
import com.example.movie.model.User;
import com.example.movie.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByMedia(Media media);
    Optional<Rating> findByUserAndMedia(User user, Media media);
}


