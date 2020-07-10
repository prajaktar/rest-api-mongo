package com.learning.restapimongo.movie;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
@Data
public class Movie {
    @Transient
    public static final String SEQUENCE_NAME = "movies_sequence";

    @Id
    private long id;
    private String name;
    private MovieStatus status = MovieStatus.NotReleased;

    public Movie(String name) {
        this.name = name;
    }
}

enum MovieStatus {
    Released,
    NotReleased
}