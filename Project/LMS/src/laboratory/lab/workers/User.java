package laboratory.lab.workers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import database.error.DBConnectError;
import database.errorhandle.CatchException;
import local.error.AuthFailed;
import local.error.UserInfoError;
import local.ui.miniwindow.MiniOption;
import standard.GlobalVariables;
import database.errorhandle.errorHandler;
import database.db.DataBase;

public class User {

    private static errorHandler eh = errorHandler.getInstance();
    // db实例
    private static DataBase dbutils = DataBase.getInstance();

    public User() {
        // 构造函数
    }

    /**
     * 登录
     * @param username 用户登录时传入的用户名
     * @param password 用户登录时传入的密码
     * @exception AuthFailed 用户认证失败
     * @exception UserInfoError 用户信息错误
     * @exception DBConnectError 数据库连接错误
     */
    public static boolean Login(String usr, String pwd) throws AuthFailed, UserInfoError, DBConnectError {
        usr = usr.trim();
        pwd = pwd.trim();
        String query = "SELECT password FROM staff WHERE username = '" + usr + "'";
        ResultSet rs = null;
        try {
            Connection connection = dbutils.getConnection();
            if (connection == null || connection.isClosed()) 
                throw new DBConnectError("Database connection is not available");
            // 查找这个用户名对应的数据库信息
            rs = dbutils.SearchDB(query);
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
        } finally {
            // 关闭连接
            try {
                rs.close();
                }
             catch (Exception e) {
                CatchException.handle(e, eh);
            }
        }

    }

    /**
     * 注册
     * @param username 用户注册时传入的用户名
     * @param password 用户注册时传入的密码
     * @exception UserInfoError 用户信息错误
     * @exception DBConnectError 数据库连接错误
     */
    public static void Register(String usr, String pwd) throws UserInfoError, DBConnectError {
        usr = usr.trim();
        pwd = pwd.trim();
        // 构造键值表
        Map<String, Object> map = new HashMap<>();
        map.put("username", usr);
        map.put("password", pwd);
        map.put("regdate", new java.sql.Timestamp(System.currentTimeMillis()));
        map.put("state", 1);
        try {
            dbutils.insertRow(GlobalVariables.getStaffTableName(), map);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    /**
     * 检查用户是否存在
     * @param username 用户名
     * @return 如果用户存在返回 true，否则返回 false
     */
    public static boolean userExists(String username) {
        String query = "SELECT COUNT(*) FROM staff WHERE username = ?";
        try (PreparedStatement stmt = dbutils.getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // 如果 COUNT(*) > 0，说明用户存在
                }
            }
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        return false; // 如果查询失败或用户不存在
    }

    public static boolean isAdmin(String name) {

        if (name.equals("root")) {
            return true;
        }
        
        if (!userExists(name)) {
            new MiniOption("User Not Found", "Cannot find this user, Please check.", MiniOption.WARNING_MESSAGE);
            return false;
        }
        if (dbutils.getUserPermission(name) == 2) {
            return true;
        } else {
            return false;
        }
    }
}
