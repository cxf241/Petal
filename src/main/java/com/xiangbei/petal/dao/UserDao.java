package com.xiangbei.petal.dao;

import com.xiangbei.petal.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    //加入新用户
    @Insert("INSERT INTO user(uname,upwd,dname) VALUES(#{uname},#{upwd},#{dname})")
    int addUser(User user);

    //查找用户
    @Select("SELECT * FROM user WHERE uname=#{uname} AND upwd=#{upwd}")
    @ResultType(User.class)
    List<User> findUser(User user);

    //查找豆瓣用户id
    @Select("SELECT id FROM banuser WHERE name like binary(#{name})")
    @ResultType(int.class)
    List<Integer> findDBUser(String name);

}
