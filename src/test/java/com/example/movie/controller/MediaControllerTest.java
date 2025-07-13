package com.example.movie.controller;

import com.example.movie.exception.*;
import com.example.movie.model.Episode;
import com.example.movie.model.Media;
import com.example.movie.model.Movie;
import com.example.movie.model.Show;
import com.example.movie.service.MediaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MediaControllerTest {
    @InjectMocks
    private MediaController mediaController;

    @Mock
    private MediaService mediaService;

    private static final Movie inputMovie = createTestMovie();

    private static Movie createTestMovie() {
        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setYear((short) 2010);
        movie.setDescription("A thief steals corporate secrets through dream-sharing technology.");
        movie.setGenre("Sci-Fi");
        movie.setAverageRating(4.8f);
        movie.setRatingsCount(2500);
        movie.setDuration(148);
        return movie;
    }


    private static final Show inputShow = createTestShow();

    private static Show createTestShow() {
        Show show = new Show();
        show.setTitle("Breaking Bad");
        show.setYear((short) 2008);
        show.setDescription("A chemistry teacher turns to making meth.");
        show.setGenre("Crime");
        show.setAverageRating(4.9f);
        show.setRatingsCount(5000);
        show.setNumber_of_episodes(62);
        show.setNumber_of_seasons(5);

        Episode ep1 = new Episode();
        Episode ep2 = new Episode();
        ep1.setShow(show);
        ep2.setShow(show);
        show.setEpisodes(new ArrayList<>(List.of(ep1, ep2)));

        return show;
    }

    @Test
    void createShowTestSuccess() {
        mediaController.createShow(inputShow);

        Mockito.verify(mediaService).createShow(inputShow);
    }

    @Test
    void createShowTestFail() {
        Mockito.doThrow(new ShowExistsException("Show already exists"))
                .when(mediaService).createShow(Mockito.any(Show.class));

        assertThrows(ShowExistsException.class, () -> {
            mediaController.createShow(inputShow);
        });
    }

    @Test
    void createMovieTestSuccess() {
        mediaController.createMovie(inputMovie);

        Mockito.verify(mediaService).createMovie(inputMovie);
    }

    @Test
    void createMovieTestFail() {
        Mockito.doThrow(new MovieExistsException("Movie already exists"))
                .when(mediaService).createMovie(Mockito.any(Movie.class));

        assertThrows(MovieExistsException.class, () -> {
            mediaController.createMovie(inputMovie);
        });
    }

    @Test
    void deleteMediaTestSuccess() {
        String title = "Inception";

        mediaController.deleteMedia(title);

        Mockito.verify(mediaService).deleteMedia(title);
    }


    @Test
    void editMovieTestSuccess() {
        mediaController.editMovie(inputMovie);

        Mockito.verify(mediaService).editMovie(inputMovie);
    }

    @Test
    void editShowTestSuccess() {
        mediaController.editShow(inputShow);

        Mockito.verify(mediaService).editShow(inputShow);
    }

    @Test
    void editMovieTestFail() {
        Mockito.doThrow(new MovieDoesNotExistException("Movie not found"))
                .when(mediaService).editMovie(Mockito.any(Movie.class));

        assertThrows(RuntimeException.class, () -> {
            mediaController.editMovie(inputMovie);
        });
    }

    @Test
    void editShowTestFail() {
        Mockito.doThrow(new ShowDoesNotExistException("Show not found"))
                .when(mediaService).editShow(Mockito.any(Show.class));

        assertThrows(RuntimeException.class, () -> {
            mediaController.editShow(inputShow);
        });
    }
}
