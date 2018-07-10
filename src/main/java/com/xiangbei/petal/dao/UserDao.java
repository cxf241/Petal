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
    @Insert("INSERT INTO user(uname,upwd) VALUES(#{uname},#{upwd})")
    int addUser(User user);

    @Select("SELECT * FROM user WHERE uname=#{uname}")
    @ResultType(User.class)
    List<User> findUser(User user);
}
