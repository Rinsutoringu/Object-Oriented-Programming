package database.db;

/*#########################
 * 对Error Handler的说明
 * 对基础封装设置抛出特定异常
 * 高级应用需要使用try代码块接住那些特定异常
 * 之后IDE报错啥加啥就是了。
 * 
 * #########################
 */
import java.sql.*;

import database.error.DBConnectError;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.GlobalVariables;

/**
 * 数据库操作类
 */
public class DataBase {
    
    private Connection connection;
    private errorHandler eh;
    private static DataBaseUtils dbutils = DataBaseUtils.getInstance();
    private static GlobalVariables GVar = GlobalVariables.getInstance();

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
            // ?connectTimeout=5000
            // String url = "jdbc:mysql://192.168.101.103:3306/LMS_sql";
            // String usr = "root";
            // String pwd = "WPR_2333";
            // 从配置文件读取数据库连接信息
            dbutils.update_DBCredentials_From_Config();

            String url = GlobalVariables.getDBConnAddress();
            String usr = GlobalVariables.getDBUser();
            String pwd = GlobalVariables.getDBPassword();

            return DriverManager.getConnection(url, usr, pwd);

        } catch (Exception e) {
            CatchException.handle(e, eh);
            return null;
        }

    }


    /**
     * 获取连接对象
     * @param errorHandler 错误处理器
     * @return 连接对象
     */
    public Connection getDB() throws DBConnectError, Exception {
        // 抓取数据库连接错误并使用错误处理器处理
        try {
            // Check if connection is null or closed
            if (!(connection != null && !connection.isClosed())) throw new DBConnectError("Failed to connect to database");
            // Check table staff existence
            if (!tableExists("staff")) throw new DBConnectError("Table 'staff' does not exist");

            if (!tableExists("Shelf")) throw new DBConnectError("Table 'Shelf' does not exist");

            return connection;

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

    // 确定表格是否存在
    public boolean tableExists(String tableName) {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, tableName, null);
            return tables.next();
        } catch (SQLException e) {
            CatchException.handle(e, eh);
        }
        return false;
    }

}
