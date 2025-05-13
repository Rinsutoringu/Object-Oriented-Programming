package database.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import local.error.AuthFailed;
import local.error.UserInfoError;

public class DataBaseUtils {
    private DataBase db;

    public DataBaseUtils(){
        // 初始化database类
        db = new DataBase();
        
    }

    /**
     * 登录验证
     * @param username 用户登录时传入的用户名
     */
    public void Login(String usr, String pwd){
        usr = usr.trim();
        pwd = pwd.trim();
        String query = "SELECT password FROM staff WHERE username = '" + usr + "'";
        try {
            // 查找这个用户名对应的数据库信息
            ResultSet rs = this.db.SearchDB(query);
            // 如果查找失败\压根没这个人则失败
            if (rs == null || !rs.next()) throw new UserInfoError("User not exist");
            // 如果找到了不止一个人的信息则失败
            if (rs.next()) throw new UserInfoError("User name duplicate");
            // 如果密码不匹配则失败
            if (!pwd.equals(rs.getString("password"))) throw new UserInfoError("Password not match");
        } catch (SQLException e) {
            // 查找失败
            throw new AuthFailed("Failed to login user", e);
        }  
    }

    /**
     * 注册
     * @param username 用户注册时传入的用户名
     */
    public void Register(String usr, String pwd){
        usr = usr.trim();
        pwd = pwd.trim();
        // 将用户名和密码插入数据库，同时为index增加1
        // 获取当前时间
        String insertQuery = "INSERT INTO staff (username, password, regdate, state) VALUES (?, ?, NOW(), 1)";
        String updateIndexQuery = "UPDATE staff SET staff_index = staff_index + 1 WHERE username = ?";
        try {

            // 创建预编译语句
            PreparedStatement insertStatement = this.db.getDB().prepareStatement(insertQuery);
            insertStatement.setString(1, usr);
            insertStatement.setString(2, pwd);
            

        } catch (SQLException e) {
            throw new UserInfoError("Failed to prepare insert statement", e);
        }

        // 尝试注册
            try {
            // 插入用户数据
            this.db.UpdateDB(insertQuery);
            // 更新索引
            this.db.UpdateDB(updateIndexQuery);
        } catch (SQLException e) {
            throw new AuthFailed("Failed to register user", e);
        }
    }
}