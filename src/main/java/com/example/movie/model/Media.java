package com.example.movie.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "media")
@DiscriminatorColumn(name = "media_type")
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

//    @NonNull
//    @Column(nullable = false)
//    @Getter @Setter
//    private String type;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private Float averageRating;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private Integer ratingsCount;
}
