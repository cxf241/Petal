/*
 * @author：陈旭峰
 *
 */
package com.xiangbei.petal.service;

import com.xiangbei.petal.pojo.User;

public interface UserService {
    boolean registerUser(User user);
    User findUser(User user);
    boolean changeUser(User user);
}
