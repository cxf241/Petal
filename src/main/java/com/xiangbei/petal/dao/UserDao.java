/*
 * @author：陈旭峰
 *
 */
package com.xiangbei.petal.dao;

import com.xiangbei.petal.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    //加入新用户
    @Insert("INSERT INTO user(uname,upwd) VALUES(#{uname},#{upwd})")
    int addUser(User user);

    //查找用户
    @Select("SELECT * FROM user WHERE uname=#{uname} AND upwd=#{upwd}")
    @ResultType(User.class)
    List<User> findUser(User user);


    @Update("UPDATE user SET upwd=#{upwd} WHERE uid=#{uid}")
    int changeUser(User user);

}
