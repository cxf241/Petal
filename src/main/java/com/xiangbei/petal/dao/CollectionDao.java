/*
 * @author：陈旭峰
 *
 */
package com.xiangbei.petal.dao;

import com.xiangbei.petal.pojo.Collection;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectionDao {
    //获取用户所有收藏电影
    @Select("SELECT movieid FROM collection WHERE userId=${uId}")
    List<Integer> getAllCollections(@Param("uId")int uId);

    //查询用户是否收藏电影
    @Select("SELECT * FROM collection WHERE userId=${uId} AND movieID=${movieId}")
    Collection getCollection(@Param("uId")int uId, @Param("movieId")int movieId);

    //用户收藏单部电影
    @Insert("INSERT INTO collection VALUES(${uId}, ${movieId})")
    int addCollection(@Param("uId")int uId, @Param("movieId")int movieId);

    //用户取消收藏电影
    @Delete("DELETE FROM collection WHERE userId=${uId} AND movieid=${movieId}")
    int deleteCollection(@Param("uId")int uId, @Param("movieId")int movieId);
}
