package com.example.movie.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private String title;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private String description;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private Integer duration;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private Integer media_id;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private Integer season_number;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private Integer episode_number;
}
