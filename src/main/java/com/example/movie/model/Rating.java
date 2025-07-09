package com.example.movie.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "\"rating\"")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter
    private User user;

//    @NonNull
//    @ManyToOne
//    @JoinColumn(name = "media_id", nullable = false)
//    @Getter @Setter
//    private Media media;
    @NonNull
    @Column(nullable = false, unique = true)
    @Getter @Setter
    private long media_id;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private float rating;
}
