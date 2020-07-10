package com.learning.restapimongo.movie;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.hamcrest.MatcherAssert.assertThat;

@DataMongoTest
class MovieRepositoryTest {
    private static final String MOVIE_NAME = "Sholay";

    @Autowired
    MovieRepository movieRepository;

    @Test
    void shouldFindMovieByName() {
        Movie movie = new Movie(MOVIE_NAME);
        movieRepository.save(movie);

        Movie fetchedMovie = movieRepository.findByName(MOVIE_NAME);

        assertThat(fetchedMovie, Is.is(movie));
    }
}