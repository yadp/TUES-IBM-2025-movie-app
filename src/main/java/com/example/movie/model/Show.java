package com.example.movie.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@DiscriminatorValue("SHOW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Show extends Media{

    public Show(@NonNull String title, @NonNull Short year, @NonNull String description, @NonNull String genre, @NonNull Float averageRating, @NonNull Integer ratingsCount, List<Episode> episodes, int number_of_episodes, int number_of_seasons) {
        super(title, year, description, genre, averageRating, ratingsCount);
        this.episodes = episodes;
        this.number_of_episodes = number_of_episodes;
        this.number_of_seasons = number_of_seasons;
    }

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter
    List<Episode> episodes;

    @Getter @Setter
    private int number_of_episodes;

    @Getter @Setter
    private int number_of_seasons;
}