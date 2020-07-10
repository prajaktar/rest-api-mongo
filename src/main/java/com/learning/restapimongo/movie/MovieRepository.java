package com.learning.restapimongo.movie;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface MovieRepository extends MongoRepository<Movie, Long> {
    Movie findByName(String movieName);
}
