package com.movies.finalproject;

import java.beans.JavaBean;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinalProjectService  implements IFinalProjectService {
    @Autowired
    private MoviesRepository moviesRepository;

    @Override
    public List<Movies> getAll() {
        return (List<Movies>)moviesRepository.findAll();
    }

    @Override
    public void addMovies(Movies movie ) {
        this.moviesRepository.save(movie);
        
    }

    @Override
    public Movies getById(Integer MOVIE_ID) {
        java.util.Optional<Movies> optional = moviesRepository.findById(MOVIE_ID);
    Movies movie = null;
    if(((java.util.Optional<Movies>) optional).isPresent()){
        movie = optional.get();

    }else{
        throw new RuntimeException("data not found:"+ MOVIE_ID);
    }
    return movie;
    }

    @Override
    public void deleteById(Integer id) {
        this.moviesRepository.deleteById(id);
    }

}
