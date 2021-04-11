package com.edu.niit.dao;

import com.edu.niit.db.JDBCUtil;
import com.edu.niit.doamin.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDao extends JDBCUtil {
    private Connection conn = null;
    private PreparedStatement ps=null;
    private int result=0;
    private ResultSet rs=null;
    public int register(User register) throws SQLException {
        String sql="insert into borrow_card(username,password,reader) value (?,?,?)";
        try {
            //获取数据库连接对象
            conn= this.getConnection();

            ps=conn.prepareStatement(sql);
            ps.setString(1,register.getUsername());
            ps.setString(2,register.getPassword());
            ps.setString(3,register.getReader());
            //执行sql
            result=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            conn.close();
            ps.close();

        }
        return result;
    }
}
