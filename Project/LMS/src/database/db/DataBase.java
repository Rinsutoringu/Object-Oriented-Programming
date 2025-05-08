package database.db;

import java.sql.*;

public class DataBase {
    private Connection connection;
    
    // 创建连接对象
    public void connect() {
        try {
            String url = "jdbc:mysql://192.168.101.103:3306/LMS_sql";
            String usr = "root";
            String pwd = "WPR_2333";
            connection = DriverManager.getConnection(url, usr, pwd);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 断开连接
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Close Success!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取连接对象
    public Connection getDB() {
        return connection;
    }

    /**
     * 查询数据库数据
     * @param query 数据库操作语句
     * @return 查询到的数组，没查到会报错
     */
    public ResultSet SearchDB(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 编辑数据库数据
     * @param query 数据库操作语句
     * @return 受到影响的行数
     */
    public int AddDB(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    } 
}

// A new Brench!

// yep, it's cool!