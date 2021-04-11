package com.edu.niit.servlet;


import com.edu.niit.service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    RegisterService userService = new RegisterService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName, password, reader, repassword;
        userName = req.getParameter("username");
        password = req.getParameter("password");
        reader = req.getParameter("reader");
        repassword = req.getParameter("repassword");
        if (password != null && password.equals(repassword)) {
        String result = userService.register(userName, password, reader);

            if ("注册成功".equals(result)) {
                resp.sendRedirect("/index.jsp?message=" + URLEncoder.encode(result, "UTF-8"));
            } else {
                req.getRequestDispatcher("/register.jsp?message=" + result).forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/register.jsp?message=" + "两次密码不相同").forward(req, resp);
        }
    }

}
