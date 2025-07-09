package com.example.movie.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Show")
public class Show extends Media{
    @Getter @Setter
    private int number_of_episodes;

    @Getter @Setter
    private int number_of_seasons;
}