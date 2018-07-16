package com.xiangbei.petal.service;

import com.xiangbei.petal.pojo.User;

public interface UserService {
    boolean registerUser(User user);
    User findUser(User user);
    int findDBUser(String name);
}
