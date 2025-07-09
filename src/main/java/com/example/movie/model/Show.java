package com.example.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import java.util.List;


@Entity
@DiscriminatorValue("SHOW")
public class Show extends Media{
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
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