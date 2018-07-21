/*
 * @author：陈旭峰
 *
 */
package com.xiangbei.petal.service;

import com.xiangbei.petal.pojo.Movie;

import java.util.List;

public interface MovieService {
    Movie getMovieById(String id);
    List<Movie> getMovies();
    List<Integer> getRecommend(int userId);

    Integer getTotalCount(String keyWord);
    List<Movie> getMovieByKeyWordPage(String keyWord, int page);

    List<Movie> getSimilarMovie(String id);
}
