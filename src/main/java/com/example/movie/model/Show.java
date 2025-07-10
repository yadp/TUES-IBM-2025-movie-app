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
    @Getter @Setter
    List<Episode> episodes;

    @Column(nullable = true)
    @Getter @Setter
    private int number_of_episodes;

    @Column(nullable = true)
    @Getter @Setter
    private int number_of_seasons;
}