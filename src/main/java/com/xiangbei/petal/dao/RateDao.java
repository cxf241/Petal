package com.xiangbei.petal.dao;

import com.xiangbei.petal.pojo.Rate;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RateDao {
    //添加评分
    @Insert("INSERT INTO rate VALUES(#{uId},#{movieId},#{score})")
    int addRate(Rate rate);

    //查询某个用户的所有评分
    @Select("SELECT * FROM rate WHERE userid=${uId}")
    @ResultType(Rate.class)
    List<Rate> getAllRate(@Param("uId")int uId);

    //查询某个用户对单个电影的评分
    @Select("SELECT score FROM rate WHERE userid=${uId} AND movieId=${movieId}")
    Double getRate(@Param("uId")int uId, @Param("movieId")int movieId);
}
