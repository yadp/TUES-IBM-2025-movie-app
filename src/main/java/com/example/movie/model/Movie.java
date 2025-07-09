package com.example.movie.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Movie")
public class Movie extends Media{
    @Getter @Setter
    private Integer duration;
}
