package com.example.movie.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import java.util.List;


@Entity
@DiscriminatorValue("SHOW")
public class Show extends Media{
    List<Episode> episodes;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private int number_of_episodes;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private int number_of_seasons;
}