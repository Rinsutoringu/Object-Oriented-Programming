package laboratory.lab.workers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.error.DBConnectError;
import database.errorhandle.CatchException;
import local.error.AuthFailed;
import local.error.UserInfoError;

import database.errorhandle.errorHandler;
import database.db.DataBase;

public class User {

    errorHandler eh = errorHandler.getInstance();
    // db实例
    DataBase db = DataBase.getInstance();

    /**
     * 登录
     * @param username 用户登录时传入的用户名
     * @param password 用户登录时传入的密码
     * @exception AuthFailed 用户认证失败
     * @exception UserInfoError 用户信息错误
     * @exception DBConnectError 数据库连接错误
     */
    public boolean Login(String usr, String pwd) throws AuthFailed, UserInfoError, DBConnectError {
        usr = usr.trim();
        pwd = pwd.trim();
        String query = "SELECT password FROM staff WHERE username = '" + usr + "'";
        try {
            Connection connection = db.getConnection();
            if (connection == null || connection.isClosed()) 
                throw new DBConnectError("Database connection is not available");
            // 查找这个用户名对应的数据库信息
            ResultSet rs = db.SearchDB(query);
            // 如果查找失败\压根没这个人则失败
            if (rs == null || !rs.next()) {
                throw new UserInfoError("User not exist");
            }
            // 如果找到了不止一个人的信息则失败
            // TODO 直接在数据库施加限制即可。
            // if (rs.next()) throw new UserInfoError("User name duplicate");
            
            // 如果密码不匹配则失败
            if (!pwd.equals(rs.getString("password"))) throw new UserInfoError("Password not match");
            return true;
        } catch (Exception e) {
            CatchException.handle(e, eh);
            return false;
        }
    }

    /**
     * 注册
     * @param username 用户注册时传入的用户名
     * @param password 用户注册时传入的密码
     * @exception UserInfoError 用户信息错误
     * @exception DBConnectError 数据库连接错误
     */
    public void Register(String usr, String pwd) throws UserInfoError, DBConnectError {
        usr = usr.trim();
        pwd = pwd.trim();
        // 将用户名和密码插入数据库，同时为index增加1
        // 获取当前时间
        String insertQuery = "INSERT INTO staff (username, password, regdate, state) VALUES (?, ?, NOW(), 1)";
        // String updateIndexQuery = "UPDATE staff SET staff_index = staff_index + 1 WHERE username = ?";
        try {

            // DEBUG
            // System.out.println("Executing query: " + insertQuery);
            // System.out.println("Username: " + usr + ", Password: " + pwd);

            // 创建预编译语句，
            PreparedStatement insertStatement = this.db.getConnection().prepareStatement(insertQuery);
            insertStatement.setString(1, usr);
            insertStatement.setString(2, pwd);
            // 执行插入操作
            insertStatement.executeUpdate();

        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }
}
