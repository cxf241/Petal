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
    public User findUser(User user) {
        if (userDao.findUser(user).size() > 0)
            return userDao.findUser(user).get(0);
        else return null;
    }

    @Override
    public boolean changeUser(User user) {
        return userDao.changeUser(user) > 0;
    }
}
