/*
 * @author：陈旭峰
 *
 */
package com.xiangbei.petal.dao;

import com.xiangbei.petal.pojo.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    //获得2018年评分前16部电影（待删除）
    @Select("SELECT * FROM movie HAVING year='(2018)' ORDER BY rating+0 DESC LIMIT 16")
    List<Movie> getMovies();

    //推荐（待删除）
    @Select("SELECT movieId from rating where userid=#{userId}")
    List<Integer> getRecommend(int userId);

    //根据关键字和页数获得电影集合
    @Select("SELECT id, name, url, rating, casts FROM movie WHERE name LIKE '${keyWord}' OR type LIKE '${keyWord}' LIMIT ${offset} , 10")
    List<Movie> getMovieByKeyWordPage(@Param("keyWord")String keyWord, @Param("offset")int offset);

    //获得某个关键字下所有电影的数量
    @Select("SELECT COUNT(*) FROM movie WHERE name LIKE '${keyWord}' OR type LIKE '${keyWord}'")
    @ResultType(Integer.class)
    Integer getTotalCount(@Param("keyWord")String keyWord);

    //根据id获得电影类型
    @Select("SELECT type FROM movie WHERE id = ${id}")
    @ResultType(String.class)
    String getTypeById(@Param("id")int id);

    //获得所有电影的id和type
    @Select("SELECT id,type FROM movie")
    List<Movie> getAllMovie();

}
