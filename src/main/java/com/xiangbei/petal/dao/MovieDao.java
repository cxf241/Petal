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

    //根据id获得电影的详细信息
    @Select("SELECT * FROM movie WHERE id=#{id}")
    @ResultType(Movie.class)
    Movie getMovieById(String id);

    //获得2018年评分前16部电影
    @Select("SELECT * FROM movie HAVING year='(2018)' ORDER BY rating+0 DESC LIMIT 16")
    List<Movie> getMovies();

    //根据关键字搜索电影
    @Select("SELECT id, name, url, rating, casts FROM movie WHERE name LIKE #{keyWord} OR type LIKE #{keyWord}")
    List<Movie> getMovieByKeyWord(String keyWord);

    @Select("SELECT movieId from rating where userid=#{userId}")
    List<Integer> getRecommend(int userId);
}
