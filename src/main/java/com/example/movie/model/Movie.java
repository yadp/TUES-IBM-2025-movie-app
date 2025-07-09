package com.example.movie.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("MOVIE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie extends Media{

    public Movie(@NonNull String title, @NonNull Short year, @NonNull String description, @NonNull String genre, @NonNull Float averageRating, @NonNull Integer ratingsCount, Integer duration) {
        super(title, year, description, genre, averageRating, ratingsCount);
        this.duration = duration;
    }

    @Getter @Setter
    private Integer duration;
}
