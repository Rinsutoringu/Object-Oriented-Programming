package database.db;

import java.sql.*;

import database.DBConnectionErrorHandler;
import database.error.DBConnectError;

public class DataBase {
    private Connection connection;

    public DataBase() {
        try {
        this.connection = connect();
        } catch (Exception e) {
            System.out.println("1/4 init error occur");
            // e.printStackTrace();
        }
        
    }
    
    // 创建连接对象
    public Connection connect() {
        try {
            // TODO 判断LMS_sql是否存在
            String url = "jdbc:mysql://192.168.101.103:3306/LMS_sql?connectTimeout=5000";
            String usr = "root";
            String pwd = "WPR_2333";
            return DriverManager.getConnection(url, usr, pwd);

        } catch (Exception e) {
            System.out.println("2/4 connect error occur");
            // e.printStackTrace();
            // throw new DBConnectError("Failed to connect to database", e);
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
            System.out.println("3/4 disconnect error occur");
            // e.printStackTrace();
            // throw new DBConnectError("Failed to disconnect database", e);
        }
    }

    /**
     * 获取连接对象
     * @param errorHandler 错误处理器
     * @return 连接对象
     */
    public Connection getDB(DBConnectionErrorHandler errorHandler) {
        // 抓取数据库连接错误并使用错误处理器处理
        try {
            if (connection != null && !connection.isClosed()) return connection;
            // 连接失败，抛出错误
            throw new DBConnectError("Failed to connect to database");
        } catch (SQLException e) {
            System.out.println("4/4 getDB error occur");
            // 处理连接错误
            // 使用错误处理器处理连接错误 错误处理器接受一个DBConnectError对象，根据其错误内容执行不同操作
            // errorHandler.handleConnectionError(new DBConnectError("Failed to connect to database", e));
        } catch (DBConnectError e) {
            System.out.println("get DBConnectError");
            // 处理连接错误
        }
        return null;
    }




}
