package com.xiangbei.petal.service;

import com.xiangbei.petal.pojo.Movie;

import java.util.List;

public interface MovieService {
    Movie getMovieById(String id);
    List<Movie> getMovies();
}
