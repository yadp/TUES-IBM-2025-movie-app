package com.example.movie.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

@Entity
@DiscriminatorValue("Show")
public class Show extends Media{
    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private int number_of_episodes;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private int number_of_seasons;
}