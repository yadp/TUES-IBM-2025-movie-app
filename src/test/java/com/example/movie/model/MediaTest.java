package com.example.movie.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MediaTest {
    private static final String MOVIE_TITLE = "The Matrix";
    private static final short MOVIE_YEAR = 1999;
    private static final String MOVIE_DESCRIPTION = "A hacker discovers the truth about his world.";
    private static final String MOVIE_GENRE = "Sci-Fi";
    private static final float MOVIE_RATING = 4.7F;
    private static final int MOVIE_RATINGS_COUNT = 1300;
    private static final int MOVIE_DURATION = 136;

    private static final String SHOW_TITLE = "Breaking Bad";
    private static final short SHOW_YEAR = 2008;
    private static final String SHOW_DESCRIPTION = "A high school chemistry teacher turned methamphetamine producer.";
    private static final String SHOW_GENRE = "Crime, Drama, Thriller";
    private static final float SHOW_RATING = 9.5F;
    private static final int SHOW_RATINGS_COUNT = 2000000;
    private static final int SHOW_NUMBER_OF_EPISODES = 62;
    private static final int SHOW_NUMBER_OF_SEASONS = 5;

    @Test
    public void testMovieCreationAndFields() {
        Movie movie = new Movie();
        movie.setTitle(MOVIE_TITLE);
        movie.setYear(MOVIE_YEAR);
        movie.setDescription(MOVIE_DESCRIPTION);
        movie.setGenre(MOVIE_GENRE);
        movie.setAverageRating(MOVIE_RATING);
        movie.setRatingsCount(MOVIE_RATINGS_COUNT);
        movie.setDuration(MOVIE_DURATION);

        assertEquals(MOVIE_TITLE, movie.getTitle());
        assertEquals(MOVIE_YEAR, movie.getYear());
        assertEquals(MOVIE_DESCRIPTION, movie.getDescription());
        assertEquals(MOVIE_GENRE, movie.getGenre());
        assertEquals(MOVIE_RATING, movie.getAverageRating());
        assertEquals(MOVIE_RATINGS_COUNT, movie.getRatingsCount());
        assertEquals(MOVIE_DURATION, movie.getDuration());
    }

    @Test
    public void testShowCreationAndFields() {
        Show show = new Show();
        show.setTitle(SHOW_TITLE);
        show.setYear(SHOW_YEAR);
        show.setDescription(SHOW_DESCRIPTION);
        show.setGenre(SHOW_GENRE);
        show.setAverageRating(SHOW_RATING);
        show.setRatingsCount(SHOW_RATINGS_COUNT);
        show.setNumber_of_episodes(SHOW_NUMBER_OF_EPISODES);
        show.setNumber_of_seasons(SHOW_NUMBER_OF_SEASONS);

        Episode ep1 = new Episode();
        Episode ep2 = new Episode();
        ep1.setShow(show);
        ep2.setShow(show);
        List<Episode> episodeList = new ArrayList<>();
        episodeList.add(ep1);
        episodeList.add(ep2);
        show.setEpisodes(episodeList);

        assertEquals(SHOW_TITLE, show.getTitle());
        assertEquals(SHOW_YEAR, show.getYear());
        assertEquals(SHOW_DESCRIPTION, show.getDescription());
        assertEquals(SHOW_GENRE, show.getGenre());
        assertEquals(SHOW_RATING, show.getAverageRating());
        assertEquals(SHOW_RATINGS_COUNT, show.getRatingsCount());
        assertEquals(SHOW_NUMBER_OF_EPISODES, show.getNumber_of_episodes());
        assertEquals(SHOW_NUMBER_OF_SEASONS, show.getNumber_of_seasons());
        assertNotNull(show.getEpisodes());
        assertEquals(2, show.getEpisodes().size());
    }
}
