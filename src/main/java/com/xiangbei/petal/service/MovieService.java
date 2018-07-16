package com.xiangbei.petal.service;

import com.xiangbei.petal.pojo.Movie;

import java.util.List;

public interface MovieService {
    Movie getMovieById(String id);
    List<Movie> getMovies();
    List<Movie> getMovieByKeyWord(String keyWord);
    List<Integer> getRecommend(int userId);
}
