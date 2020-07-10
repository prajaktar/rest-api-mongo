package com.learning.restapimongo.movie;

import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {

        this.movieRepository = movieRepository;
    }

    public Movie findMovieByName(String movieName) {
        return movieRepository.findByName(movieName);
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
