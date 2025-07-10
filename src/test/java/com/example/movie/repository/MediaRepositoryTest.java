package com.example.movie.repository;

import com.example.movie.model.Media;
import com.example.movie.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class MediaRepositoryTest {
    private static final String TITLE = "The Matrix";
    private static final short YEAR = 1999;
    private static final String DESCRIPTION = "A hacker discovers the truth about his world.";
    private static final String GENRE = "Sci-Fi";
    private static final float RATING = 4.7F;
    private static final int RATINGS_COUNT = 1300;
    private static final int DURATION = 136;

    @Autowired
    private MediaRepository mediaRepository;

    @Test
    void testSaveAndFindByTitle() {
        Movie movie = new Movie();
        movie.setTitle(TITLE);
        movie.setYear(YEAR);
        movie.setDescription(DESCRIPTION);
        movie.setGenre(GENRE);
        movie.setAverageRating(RATING);
        movie.setRatingsCount(RATINGS_COUNT);
        movie.setDuration(DURATION);

        mediaRepository.save(movie);

        Media found = mediaRepository.findByTitle(TITLE);

        assertNotNull(found);
        assertEquals(TITLE, found.getTitle());
        assertEquals(DESCRIPTION, found.getDescription());
    }


}
