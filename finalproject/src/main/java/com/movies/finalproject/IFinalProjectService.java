package com.movies.finalproject;

import java.util.List;

public interface IFinalProjectService {
    List<Movies> getAll();
    void addMovies(Movies movie);   
    Movies getById(Integer id);
    void deleteById(Integer id);
}
