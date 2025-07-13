package com.example.movie.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@DiscriminatorValue("SHOW")
//@RequiredArgsConstructor
@NoArgsConstructor
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