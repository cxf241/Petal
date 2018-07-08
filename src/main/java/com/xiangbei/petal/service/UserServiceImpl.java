package com.xiangbei.petal.service;

import com.xiangbei.petal.dao.UserDao;
import com.xiangbei.petal.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public boolean registerUser(User user) {
        return userDao.addUser(user) > 0;
    }

    @Override
    public boolean findUser(User user) {
        return userDao.findUser(user).size() > 0;
    }
}
