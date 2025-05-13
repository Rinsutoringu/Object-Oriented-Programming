package database.db;

import java.sql.*;

import database.error.DBConnectError;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;

import java.sql.SQLException;
/*#########################
 * 对Error Handler的说明
 * 对基础封装设置抛出特定异常
 * 高级应用需要使用try代码块接住那些特定异常
 * 之后IDE报错啥加啥就是了。
 * 
 * #########################
 */

/**
 * 数据库操作类
 */
public class DataBase {
    
    private Connection connection;
    private errorHandler eh;

    // TODO 提供降级逻辑
    // 我不想做了!
    public DataBase() {
        // 实例化处理对象
        eh = errorHandler.getInstance();
        try {
            if (connection != null && !connection.isClosed()) return;
            this.connection = connect();
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }
    
    // 创建连接对象
    public Connection connect() throws DBConnectError, Exception {
        try {
            if (connection != null && !connection.isClosed()) return connection;
            // TODO 判断LMS_sql是否存在
            // ?connectTimeout=5000
            String url = "jdbc:mysql://192.168.101.103:3306/LMS_sql";
            String usr = "root";
            String pwd = "WPR_2333";
            return DriverManager.getConnection(url, usr, pwd);
        }
        // 肯定会有异常没接住的，但是不要紧，丢到下一级再处理
        catch (Exception e) {CatchException.handle(e, eh);}
        return null;

    }


    /**
     * 获取连接对象
     * @param errorHandler 错误处理器
     * @return 连接对象
     */
    public Connection getDB() throws DBConnectError, Exception {
        // 抓取数据库连接错误并使用错误处理器处理
        try {
            if (connection != null && !connection.isClosed()) return connection;
            // 连接失败，抛出错误
            throw new DBConnectError("Failed to connect to database");
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        return null;
    }

    // 断开连接
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Close Success!");
            }
        } catch (SQLException e) {
            throw new DBConnectError("Failed to disconnect database", e);
        }
    }


}
