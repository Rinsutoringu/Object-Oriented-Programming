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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.error.DBConnectError;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.utils.CfgIOutils;
import standard.GlobalVariables;

/**
 * 数据库操作类
 * 主要用于登录和注册
 * 数据库结构
 * staff_index | username | password | regdate | state
 * 其中staff_index是一个自增的索引，表示用户的注册编号
 * 数据库操作指令: ALTER TABLE staff MODIFY staff_index INT AUTO_INCREMENT;
 */
public class DataBase {
    
    // 数据库连接对象
    private Connection connection;
    private errorHandler eh = errorHandler.getInstance();
    // TODO 提供降级逻辑
    // 我不想做了!

    private static DataBase instance;
    private DataBase() {
        // 实例化处理对象
        // 不要直接连接，会出问题的
        // 等完全初始化了再连接
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            // 如果发现链接有问题就进行重建
            if (connection == null || connection.isClosed()) connection = createConnect();
            return connection;
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        return null;
    }

    // 创建连接对象
    public Connection createConnect() throws DBConnectError, Exception {
        try {
            // ?connectTimeout=5000
            // String url = "jdbc:mysql://192.168.101.103:3306/LMS_sql";
            // String usr = "root";
            // String pwd = "WPR_2333";
            // 从配置文件读取数据库连接信息
            this.update_DBCredentials_From_Config();

            String url = GlobalVariables.getDBConnAddress();
            String usr = GlobalVariables.getDBUser();
            String pwd = GlobalVariables.getDBPassword();

            System.out.println("DB URL: " + url);
            System.out.println("DB User: " + usr);
            System.out.println("DB Password: " + pwd);

            if (url == null || usr == null || pwd == null) {
                throw new DBConnectError("数据库连接参数有空值，请检查配置加载流程。");
            }

            return DriverManager.getConnection(url, usr, pwd);

        } catch (Exception e) {
            CatchException.handle(e, eh);
            return null;
        }

    }

    // 断开连接
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                this.getConnection();
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

    /**
     * 查询数据库数据
     * @param query 数据库操作语句
     * @return 查询到的ResultSet类型数组，没查到会报错
     */
    public ResultSet SearchDB(String query) throws SQLException, SQLTimeoutException, DBConnectError, Exception{
        try {
            // 获取连接对象
            Connection connection = this.getConnection();
            if (connection == null || connection.isClosed()) {
                throw new DBConnectError("Database connection is not available");
            }
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }

        return null;
    }

    /**
     * 编辑数据库数据
     * @param query 数据库操作语句
     * @return 受到影响的行数
     */
    public int UpdateDB(String query) throws SQLException {
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to update database", e);
        }
    } 

    /**
     * 从配置文件加载数据库连接信息
     */
    public void update_DBCredentials_From_Config() {

        // 优先读取配置文件
        try {
            Map<String,Map<String, String>> map;
            map = CfgIOutils.readjson("appconfig.cfg");

            // 检查map是否为空
            if (map == null) return;

            Map<String, String> dbconfig = map.get("dbconfig");

            // 检查dbconfig是否存在
            if (dbconfig == null) return;

            String url = dbconfig.get("dbaddr");
            String user = dbconfig.get("dbuser");
            String password = dbconfig.get("dbpassword");
            String port = dbconfig.get("dbport");
            String dbtype = dbconfig.get("dbtype");

            // 保存这些到全局变量

            GlobalVariables.setDBUrl(url);
            GlobalVariables.setDBUser(user);
            GlobalVariables.setDBPassword(password);
            GlobalVariables.setDBPort(port);
            GlobalVariables.setDBType(dbtype);

            } catch (Exception e) {
                // e.printStackTrace();
                return;
            }

        }


    /**
     * 保存数据库连接信息到配置文件
     */
    public void addDBCredentials(String dbtype, String url, String user, String password, String port) {
        Map<String,Map<String, String>> map = CfgIOutils.readjson("appconfig.cfg");

        if (map == null) map = new HashMap<>();

        Map<String, String> dbconfig = map.get("dbconfig");

        if (dbconfig == null) dbconfig = new HashMap<>();

        dbconfig.put("dbtype", dbtype);
        dbconfig.put("dbaddr", url);
        dbconfig.put("dbuser", user);
        dbconfig.put("dbpassword", password);
        dbconfig.put("dbport", port);
        map.put("dbconfig", dbconfig);

        CfgIOutils.writejson("appconfig.cfg", map);
        
    }

    /**
     * 获取数据库中的注册用户数量
     * @return 用户数量
     */
    public int getNumOfUsers() {
        String checkmsg = "SELECT COUNT(*) FROM staff";
        try {
            ResultSet rs = this.SearchDB(checkmsg);
            // 检查 ResultSet 是否有数据
            if (rs != null && rs.next()) {
                return rs.getInt(1); // 获取第一列的计数值
            }
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        // 如果 ResultSet 为空或查询失败，返回默认值 0
        return 0;
    }

    /**
     * 获取数据库中记录的物资量
     * @return 物资量
     */
    public int getNumOfObjects() {
        String checkmsg = "SELECT COUNT(*) FROM Shelf";
        try {
            ResultSet rs = this.SearchDB(checkmsg);
            // 检查 ResultSet 是否有数据
            if (rs != null && rs.next()) {
                return rs.getInt(1); // 获取第一列的计数值
            }
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        // 如果 ResultSet 为空或查询失败，返回默认值 0
        return 0;
    }

    /**
     * 从数据库拉取库存表
     */
    public List<Object[]> queryShelfTable() {
        List<Object[]> result = new ArrayList<>();
        String query = "SELECT obj_name, obj_number, obj_lastuptime, lastuser FROM shelf";
        ResultSet rs = null;
        try {
            rs = this.SearchDB(query);
            while (rs!= null && rs.next()) {
                // 获取一行数据
                Object[] row = new Object[4];
                row[0] = rs.getString("obj_name");
                row[1] = rs.getInt("obj_number");
                row[2] = rs.getString("obj_lastuptime");
                row[3] = rs.getString("lastuser");
                // 将数据添加到结果列表中
                result.add(row);
            }
        } catch (Exception e) {
            CatchException.handle(e, eh);
        } finally {
            // 关闭ResultSet
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                CatchException.handle(e, eh);
            }
        }
        return result;
    }
}