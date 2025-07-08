package com.example.movie.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "media")
@NoArgsConstructor
@RequiredArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private String title;

    @Column(nullable = false)
    @Getter @Setter
    private short year;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private String description;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private String genre;
}
