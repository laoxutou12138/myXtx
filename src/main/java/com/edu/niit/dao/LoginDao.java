package com.edu.niit.dao;

import com.edu.niit.db.JDBCUtil;
import com.edu.niit.doamin.Admin;
import com.edu.niit.doamin.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName LoginDao
 * @Description TODO
 * @Author 28380
 * @Date 2021/3/23
 **/

public class LoginDao {

    public User selectOne(String username) {
        User user = null;
        try (ResultSet resultSet =
                     JDBCUtil.getInstance().executeQueryRS("select " +
                                     "* " +
                                     "from " +
                                     "borrow_card where username=?",
                             new Object[]{username})) {

            while (resultSet.next()) {
                user = new User(resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("reader"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    public Admin selectOne(String username, String password) {
        Admin admin = null;
        try (ResultSet resultSet =
                     JDBCUtil.getInstance().executeQueryRS("select " +
                                     "* " +
                                     "from " +
                                     "admin where username=?",
                             new Object[]{username})) {

            while (resultSet.next()) {
                admin = new Admin(resultSet.getString("username"),
                        resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }
    public int register(User user){
        int result=JDBCUtil.getInstance().executeUpdate("INSERT IGNORE INTO `borrow_card` (username," +
                "`password`, reader) \n "+
                "SELECT ?,?,? WHERE NOT EXISTS (\n" +
                "SELECT 1 FROM borrow_card WHERE `username`=?)",
                new Object[]{user.getUsername(),user.getPassword(),user.getReader(),user.getUsername()});
        return result;
    }
}