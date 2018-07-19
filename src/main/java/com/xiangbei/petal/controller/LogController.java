package com.xiangbei.petal.controller;

import com.xiangbei.petal.pojo.User;
import com.xiangbei.petal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class LogController {

    @Autowired
    UserService userService;

    //用户登录
    @RequestMapping(value = "doLogin",method = RequestMethod.POST)
    public boolean login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        User user = new User();
        user.setUname(request.getParameter("uname"));
        user.setUpwd(request.getParameter("upwd"));

        if (userService.findUser(user) != null) {//登录成功
            //设置当前用户
            session.setAttribute("currentUser",userService.findUser(user));
            return true;
        }else return false;
    }

    //用户注册
    @RequestMapping(value = "doRegister",method = RequestMethod.POST)
    public boolean register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //可能有乱码问题
        request.setCharacterEncoding("UTF-8");
        User user = new User();
        user.setUname(request.getParameter("uname"));
        user.setUpwd(request.getParameter("upwd"));
        if (userService.findUser(user) != null) {
            return false;
        }
        else {
            return userService.registerUser(user);
        }
    }

    //用户注销
    @RequestMapping(value = "doLogout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.getSession().setAttribute("currentUser", null);
        response.sendRedirect("/main.html");
    }

    //返回当前用户
    @RequestMapping(value = "user")
    public User getCurrentUser(HttpServletRequest request) {
        return (User)request.getSession().getAttribute("currentUser");
    }
}
