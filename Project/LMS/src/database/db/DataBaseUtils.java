package database.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseUtils {
    private DataBase db;

    public DataBaseUtils(){
        // 初始化database类
        DataBase db = new DataBase();
        
    }

    /**
     * 登录验证
     * @param username 用户登录时传入的用户名
     */
    public boolean Login(String usr, String pwd){
        String query = "SELECT password FROM staff WHERE username = '" + usr + "'";
        try {
            // 查找这个用户名对应的数据库信息
            ResultSet rs = this.db.SearchDB(query);
            // 如果查找失败\压根没这个人则失败
            if (rs == null || !rs.next()) return false;
            // 如果找到了不止一个人的信息则失败
            if (rs.next()) {System.out.println("检测到用户重名，请处理");return false;}
            // 如果密码不匹配则失败
            if (usr != rs.getString("password")) return false;
            return false;
        } catch (SQLException e) {
            // 输出错误信息
            e.printStackTrace();
            // 查找失败
            return false;
        }  
    }
}
