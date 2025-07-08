package com.example.movie.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NonNull;

public class Movie extends Media{
    @NonNull
    @Column(nullable = false)
    @Getter
    private int duration;
}
