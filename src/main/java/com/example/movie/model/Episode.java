package com.example.movie.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "episodes")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private String title;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private String description;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private Integer duration;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private Integer season_number;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private Integer episode_number;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;
}
