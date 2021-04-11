package com.edu.niit.service;

import com.edu.niit.dao.LoginDao;
import com.edu.niit.dao.RegisterDao;
import com.edu.niit.doamin.User;

public class RegisterService {
    private RegisterDao registerDao=new RegisterDao();
    LoginDao loginDao = new LoginDao();

    public String  register(String username,String password, String reader) {
        User user=new User(username,password,reader);
        int  result=loginDao.register(user);
        if (result>0){
            return "注册成功";
        }else {
            return "用户名已存在";
        }
    }
}
