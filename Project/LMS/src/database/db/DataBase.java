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
     * 查询数据库数据-已弃用
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
     * 通用数据库查询方法
     * @param tableName 表名
     * @param whereMap 条件map，key为字段名，value为字段值
     * @return 查询结果ResultSet（用完后记得关闭）
     */
    public ResultSet queryRow(String tableName, Map<String, Object> whereMap) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(tableName);
        if (whereMap != null && !whereMap.isEmpty()) {
            sql.append(" WHERE ");
            int i = 0;
            for (Map.Entry<String, Object> entry : whereMap.entrySet()) {
                if (i++ > 0) sql.append(" AND ");
                sql.append(entry.getKey()).append("='").append(entry.getValue()).append("'");
            }
        }
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql.toString());
    }

    /**
     * 编辑数据库数据
     * @param tableName 表名
     * @param whereMap 条件map，key为字段名，value为字段值
     * @param updateMap 更新map，key为字段名，value为字段值
     */
    public void updateRow(String tableName, Map<String, Object> whereMap, Map<String, Object> updateMap) throws Exception {
        if (whereMap == null || whereMap.isEmpty() || updateMap == null || updateMap.isEmpty()) {
            throw new IllegalArgumentException("whereMap和updateMap不能为空");
        }
        StringBuilder sql = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        int i = 0;
        for (Map.Entry<String, Object> entry : updateMap.entrySet()) {
            if (i++ > 0) sql.append(", ");
            sql.append(entry.getKey()).append("='").append(entry.getValue()).append("'");
        }
        sql.append(" WHERE ");
        i = 0;
        for (Map.Entry<String, Object> entry : whereMap.entrySet()) {
            if (i++ > 0) sql.append(" AND ");
            sql.append(entry.getKey()).append("='").append(entry.getValue()).append("'");
        }
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            System.out.println("SQL: " + sql.toString());
            stmt.executeUpdate(sql.toString());
        }
    }

    /**
     * 插入数据库数据
     * @param tableName 表名
     * @param valueMap 插入map，key为字段名，value为字段值
     */
    public void insertRow(String tableName, Map<String, Object> valueMap) throws Exception {
        if (valueMap == null || valueMap.isEmpty()) {
            throw new IllegalArgumentException("valueMap不能为空");
        }
        StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        StringBuilder values = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
            if (i++ > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append(entry.getKey());
            values.append("'").append(entry.getValue()).append("'");
        }
        sql.append(") VALUES (").append(values).append(")");
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql.toString());
        }
    }

    /**
     * 通用数据库删除方法
     * @param tableName 表名
     * @param whereMap 条件map，key为字段名，value为字段值
     */
    public void deleteRow(String tableName, Map<String, Object> whereMap) throws Exception {
        if (whereMap == null || whereMap.isEmpty()) {
            throw new IllegalArgumentException("whereMap不能为空");
        }
        StringBuilder sql = new StringBuilder("DELETE FROM ").append(tableName).append(" WHERE ");
        int i = 0;
        for (String key : whereMap.keySet()) {
            if (i++ > 0) sql.append(" AND ");
            sql.append(key).append("=?");
        }
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            int idx = 1;
            for (Object value : whereMap.values()) {
                pstmt.setObject(idx++, value);
            }
            pstmt.executeUpdate();
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