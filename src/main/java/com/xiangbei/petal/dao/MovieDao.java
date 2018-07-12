package com.xiangbei.petal.dao;

import com.xiangbei.petal.pojo.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MovieDao {
    @Select("SELECT * FROM movie WHERE id=#{id}")
    @ResultType(Movie.class)
    Movie getMovieById(String id);

    @Select("SELECT * FROM movie HAVING year='(2018)' ORDER BY rating+0 DESC LIMIT 16")
    List<Movie> getMovies();
}
