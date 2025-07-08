package com.example.movie.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

@Entity
@DiscriminatorValue("MOVIE")
public class Movie extends Media{
    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private Integer duration;
}
