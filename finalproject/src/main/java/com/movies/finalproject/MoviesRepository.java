package com.movies.finalproject;

import org.springframework.data.repository.CrudRepository;

public interface MoviesRepository extends CrudRepository <Movies, Integer> {
    
}
