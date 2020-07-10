package com.learning.restapimongo.movie;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {

        this.movieService = movieService;
    }

    @GetMapping(value = "/movies")
    public ResponseEntity getMovieByName(@RequestParam(value = "name") String movieName) {
        Movie movie = movieService.findMovieByName(movieName);

        return ResponseEntity.ok(movie);
    }

    @PostMapping("/movies/save")
    public ResponseEntity save(@RequestBody MovieRequest body) {
        Movie movie = movieService.saveMovie(new Movie(body.getName()));

        return ResponseEntity.ok(movie);
    }
}
