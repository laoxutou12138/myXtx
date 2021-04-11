package com.edu.niit.service;

import com.edu.niit.dao.LoginDao;
import com.edu.niit.doamin.Admin;
import com.edu.niit.doamin.User;

import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginService
 * @Description TODO
 * @Author 28380
 * @Date 2021/3/23
 **/

public class LoginService {

    private LoginDao loginDao = new LoginDao();

    public String login(String username, String password,
                        HttpSession session) {
        User user = loginDao.selectOne(username);
        if (user == null) {
            return "用户不存在";
        } else {
            if (password.equals(user.getPassword())) {
                session.setAttribute("user", user);
                session.setAttribute("isLogin", true);
                return "1";
            } else {
                return "密码错误";
            }
        }
    }

    public String adminLogin(String username, String password,
                             HttpSession session) {
        Admin admin = loginDao.selectOne(username,password);
        if (admin == null) {
            return "用户不存在";
        } else {
            if (password.equals(admin.getPassword())) {
                session.setAttribute("admin", admin);
                session.setAttribute("isLogin", true);
                return "1";
            } else {
                return "密码错误";
            }
        }
    }
}
