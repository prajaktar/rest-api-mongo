package com.learning.restapimongo.movie;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class MovieTest {
    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie("Sholay");
    }

    @Test
    void shouldReturnDefaultMovieStatusAsNotReleased() {
        assertThat(movie.getStatus(), Is.is(MovieStatus.NotReleased));
    }

    @Test
    void shouldReturnUpdatedMovieStatusAsReleased() {
        movie.setStatus(MovieStatus.Released);
        assertThat(movie.getStatus(), Is.is(MovieStatus.Released));
    }
}