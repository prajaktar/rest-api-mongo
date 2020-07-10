package com.learning.restapimongo.movie;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MovieServiceTest {
    @Mock
    MovieRepository movieRepository;

    @Mock
    Movie movie;

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieService = new MovieService(movieRepository);
    }

    @Test
    void shouldCallMovieRepository() {
        when(movieRepository.findByName(anyString()))
                .thenReturn(movie);

        Movie movie = movieService.findMovieByName("MovieName");

        assertThat(movie, Is.is(this.movie));
        Mockito.verify(movieRepository).findByName("MovieName");
    }

    @Test
    void shouldSaveGivenMovie() {
        when(movieRepository.save(any()))
                .thenReturn(movie);
        Movie actual = movieService.saveMovie(movie);

        assertThat(actual, Is.is(movie));
        verify(movieRepository).save(movie);
    }
}