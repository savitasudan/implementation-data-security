package com.movies.finalproject;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "MOVIES")
public class Movies {

    @Id
    //@Column(value = "MOVIE_Id")
    private int MOVIE_ID;

    //@Column(value = "MOVIE_NAME")
    private String MOVIE_NAME;

    public int getMOVIE_ID() {
        return MOVIE_ID;
    }

    public void setMOVIE_ID(int movieId) {
        this.MOVIE_ID = movieId;
    }

    public String getMOVIE_NAME() {
        return MOVIE_NAME;
    }

    public void setMOVIE_NAME(String MOVIE_NAME) {
        this.MOVIE_NAME = MOVIE_NAME;
    }

    public String getMOVIE_DESCRIPTION() {
        return MOVIE_DESCRIPTION;
    }

    public void setMOVIE_DESCRIPTION(String MOVIE_DESCRIPTION) {
        this.MOVIE_DESCRIPTION = MOVIE_DESCRIPTION;
    }

    public String getMOVIE_COST() {
        return MOVIE_COST;
    }

    public void setMOVIE_COST(String MOVIE_COST) {
        this.MOVIE_COST = MOVIE_COST;
    }

    public String getMOVIE_RATING() {
        return MOVIE_RATING;
    }

    public void setMOVIE_RATING(String MOVIE_RATING) {
        this.MOVIE_RATING = MOVIE_RATING;
    }

    //@Column(value = "MOVIE_DESCRIPTION")
    private String MOVIE_DESCRIPTION;

    //@Column(value = "MOVIE_COST")
    private String MOVIE_COST;

    //@Column(value = "MOVIE_RATING")
    private String MOVIE_RATING;

    @Override
    public String toString() {
        return "Movies [movieId=" + MOVIE_ID + ", name=" + MOVIE_NAME + ", description=" + MOVIE_DESCRIPTION + ", MOVIE_COST=" + MOVIE_COST
                + ", rating=" + MOVIE_RATING + "]";
    }


}

