package com.learning.restapimongo.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class MovieControllerTest {

    @Mock
    MovieService movieService;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new MovieController(movieService)).build();
    }

    @Test
    void shouldReturnGivenMovie() throws Exception {
        Movie movie = new Movie("Sholay");
        Mockito.when(movieService.findMovieByName(anyString()))
                .thenReturn(movie);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/movies?name=Sholay"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\"name\": \"Sholay\"}"));

        Mockito.verify(movieService).findMovieByName("Sholay");
    }

    @Test
    void shouldSaveGivenMovie() throws Exception {
        Movie movie = new Movie("Don");
        Mockito.when(movieService.saveMovie(any()))
                .thenReturn(movie);
        String movieJson = "{\"name\": \"Don\"}";

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/movies/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(movieJson))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(movieJson));

        Mockito.verify(movieService).saveMovie(movie);
    }
}