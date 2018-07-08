package com.xiangbei.petal.service;

import com.xiangbei.petal.pojo.User;

public interface UserService {
    boolean registerUser(User user);
    boolean findUser(User user);
}
