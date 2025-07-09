package com.example.movie.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MediaTest {
    private static final String TITLE = "The Matrix";
    private static final short YEAR = 1999;
    private static final String DESCRIPTION = "A hacker discovers the truth about his world.";
    private static final String GENRE = "Sci-Fi";
    private static final String TYPE = "Movie";
    private static final float RATING = 4.7F;
    private static final int RATINGS_COUNT = 1300;

    @Test
    void testMedia() {
        Media media = new Media(TITLE, YEAR, DESCRIPTION, GENRE, TYPE, RATING, RATINGS_COUNT);
        assertEquals(TITLE, media.getTitle());
        assertEquals(YEAR, media.getYear());
        assertEquals(DESCRIPTION, media.getDescription());
        assertEquals(GENRE, media.getGenre());
        assertEquals(TYPE, media.getType());
        assertEquals(RATING, media.getAverageRating());
        assertEquals(RATINGS_COUNT, media.getRatingsCount());
    }
}
