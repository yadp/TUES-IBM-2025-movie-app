package com.example.movie.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "\"watch_history\"")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class WatchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter
    private User user;


    @NonNull
    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    @Getter @Setter
    private Media media;

    @NonNull
    @Column(nullable = false)
    @Getter @Setter
    private LocalDate date;
}
