package com.learning.restapimongo.movie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class MovieControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MovieRepository movieRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Movie movie = new Movie("Sholay");

    @AfterEach
    void tearDown() {
        movieRepository.deleteAll();
    }

    @Test
    void shouldFindGivenMovie() throws URISyntaxException, JsonProcessingException, JSONException {
        movieRepository.save(movie);
        RequestEntity<MovieRequest> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI("/api/v1/movies?name=Sholay"));
        ResponseEntity<Object> response = testRestTemplate.exchange(requestEntity, Object.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        String actualJsonString = objectMapper.writeValueAsString(response.getBody());
        String expectedJsonString = "{\"id\":0, \"name\":\"Sholay\", \"status\":\"NotReleased\"}";
        JSONAssert.assertEquals(expectedJsonString, actualJsonString, false);

    }

    @Test
    void shouldSaveTheGivenMovie() throws URISyntaxException {
        MovieRequest movieRequest = new MovieRequest();
        movieRequest.setName("ABC");

        RequestEntity<MovieRequest> requestEntity = new RequestEntity<>(
                movieRequest, HttpMethod.POST, new URI("/api/v1/movies/save")
        );
        ResponseEntity<Object> response = testRestTemplate.exchange(requestEntity, Object.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
}