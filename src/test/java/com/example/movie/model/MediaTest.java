package com.example.movie.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MediaTest {
    private static final String TITLE = "The Matrix";
    private static final short YEAR = 1999;
    private static final String DESCRIPTION = "A hacker discovers the truth about his world.";
    private static final String GENRE = "Sci-Fi";
    private static final float RATING = 4.7F;
    private static final int RATINGS_COUNT = 1300;

    @Test
    void testMovie() {
        Movie movie = new Movie(TITLE, YEAR, DESCRIPTION, GENRE, RATING, RATINGS_COUNT, 136);
        assertEquals(TITLE, movie.getTitle());
        assertEquals(YEAR, movie.getYear());
        assertEquals(DESCRIPTION, movie.getDescription());
        assertEquals(GENRE, movie.getGenre());
        assertEquals(RATING, movie.getAverageRating());
        assertEquals(RATINGS_COUNT, movie.getRatingsCount());
        assertEquals(136, movie.getDuration());
    }

    @Test
    void testShow() {
        List<Episode> episodes = new ArrayList<>();
        episodes.add(new Episode("Ozymandias", "Something Happens", 40, 5, 14));
        Show show = new Show(TITLE, YEAR, DESCRIPTION, GENRE, RATING, RATINGS_COUNT, episodes, 187, 12);
        assertEquals(TITLE, show.getTitle());
        assertEquals(YEAR, show.getYear());
        assertEquals(DESCRIPTION, show.getDescription());
        assertEquals(GENRE, show.getGenre());
        assertEquals(RATING, show.getAverageRating());
        assertEquals(RATINGS_COUNT, show.getRatingsCount());
        assertEquals(episodes, show.episodes);
    }
}
