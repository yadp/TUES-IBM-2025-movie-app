package com.example.movie.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "media")
@DiscriminatorColumn(name = "media_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private String title;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private Short year;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private String description;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private String genre;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private String type;

    @NonNull
    @Column(nullable = false)
    @Getter
    private Float averageRating;

    @NonNull
    @Column(nullable = false)
    @Getter
    private Integer ratingsCount;

    public Media(@NonNull String title, @NonNull Short year, @NonNull String description, @NonNull String genre, @NonNull Float averageRating, @NonNull Integer ratingsCount) {
        this.title = title;
        this.year = year;
        this.description = description;
        this.genre = genre;
        this.averageRating = averageRating;
        this.ratingsCount = ratingsCount;
    }
}
