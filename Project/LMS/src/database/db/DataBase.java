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
import java.util.Properties;

import javax.swing.JOptionPane;

import database.error.DBConnectError;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.ui.miniwindow.MiniOption;
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
        GlobalVariables.getInstance();
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
            
            if (!isTableExists("staff")) createStaffTableIfNotExists();
            if (!isTableExists("shelf")) createShelfTableIfNotExists();
            
            return connection;
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        return null;
    }

    // 获取驱动类名
    private String getDriverClass() {
        String dbType = GlobalVariables.getDBType();
        switch (dbType) {
            case "MySQL":
                return "com.mysql.cj.jdbc.Driver";
            case "PostgreSQL":
                return "org.postgresql.Driver";
            case "SQLite":
                return "org.sqlite.JDBC";
            default:
                return "com.mysql.cj.jdbc.Driver";
        }
    }

    // 获取连接字符串
    private String getConnectionUrl() {
        String dbType = GlobalVariables.getDBType();
        String url = GlobalVariables.getDBUrl();
        String port = GlobalVariables.getDBPort();
        String dbsubname = GlobalVariables.getDBSubName();
        switch (dbType) {
            case "MySQL":
                return "jdbc:mysql://" + url + ":" + port + "/" + dbsubname + "?useSSL=false&serverTimezone=UTC";
            case "PostgreSQL":
                return "jdbc:postgresql://" + url + ":" + port + "/" + dbsubname;
            case "SQLite":
                return "jdbc:sqlite:" + url; // SQLite只需要文件路径
            default:
                return "";
        }
    }

    // 创建连接对象
public Connection createConnect() throws DBConnectError, Exception {
    try {
        this.update_DBCredentials_From_Config();
        String driver = getDriverClass();
        Class.forName(driver);
        String url = getConnectionUrl();
        String usr = GlobalVariables.getDBUser();
        String pwd = GlobalVariables.getDBPassword();
        String dbType = GlobalVariables.getDBType();

        if ("SQLite".equalsIgnoreCase(dbType)) {
            if (usr != null && !usr.isEmpty()) {
                Properties props = new Properties();
                props.setProperty("user", usr);
                if (pwd != null && !pwd.isEmpty()) {
                    props.setProperty("password", pwd);
                }
            }
            return DriverManager.getConnection(url);
        } else {
            return DriverManager.getConnection(url, usr, pwd);
        }
    } catch (Exception e) {
        CatchException.handle(e, eh);
        return null;
    }
}

    // 获取当前时间的SQL
    public String getNowFunction() {
        String dbType = GlobalVariables.getDBType();
        switch (dbType) {
            case "MySQL":
                return "NOW()";
            case "PostgreSQL":
                return "CURRENT_TIMESTAMP";
            case "SQLite":
                return "datetime('now')";
            default:
                return "NOW()";
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
    /**
     * 检查表格是否存在
     * @param tableName 要检查的表名
     * @return 如果表存在则返回true，否则返回false
     */
    public boolean isTableExists(String tableName) {
        try {
            if (connection == null || connection.isClosed()) {
                return false;
            }
            DatabaseMetaData metaData = connection.getMetaData();
            String dbType = GlobalVariables.getDBType();
            ResultSet tables;
            if ("SQLite".equalsIgnoreCase(dbType)) {
                tables = metaData.getTables(null, null, tableName, new String[]{"TABLE"});
                if (!tables.next()) {
                    tables = metaData.getTables(null, null, tableName.toLowerCase(), new String[]{"TABLE"});
                }
            } else {
                tables = metaData.getTables(null, null, tableName, new String[]{"TABLE"});
            }
            boolean exists = tables.next();
            tables.close();
            return exists;
        } catch (SQLException e) {
            CatchException.handle(e, eh);
        }
        return false;
    }

    // 创建staff表格
    public void createStaffTableIfNotExists() {
        String dbType = GlobalVariables.getDBType();
        String sql;
        switch (dbType) {
            case "MySQL":
                sql = "CREATE TABLE IF NOT EXISTS staff (" +
                        "staff_index INT AUTO_INCREMENT PRIMARY KEY," +
                        "username VARCHAR(20)," +
                        "password TEXT," +
                        "regdate DATETIME," +
                        "state INT" +
                    ")";
                break;
            case "PostgreSQL":
                sql = "CREATE TABLE IF NOT EXISTS staff (" +
                        "staff_index SERIAL PRIMARY KEY," +
                        "username VARCHAR(20)," +
                        "password TEXT," +
                        "regdate TIMESTAMP," +
                        "state INT" +
                    ")";
                break;
            case "SQLite":
                sql = "CREATE TABLE IF NOT EXISTS staff (" +
                        "staff_index INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "username VARCHAR(20)," +
                        "password TEXT," +
                        "regdate DATETIME," +
                        "state INTEGER" +
                    ")";
                break;
            default:
                throw new UnsupportedOperationException("Unsupported DB type: " + dbType);
        }
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    // 创建Shelf表格
    public void createShelfTableIfNotExists() {
        String dbType = GlobalVariables.getDBType();
        String sql;
        switch (dbType) {
            case "MySQL":
                sql = "CREATE TABLE IF NOT EXISTS shelf (" +
                        "shelf_index INT AUTO_INCREMENT PRIMARY KEY," +
                        "obj_name TEXT," +
                        "obj_number INT," +
                        "obj_lastuptime DATETIME," +
                        "lastuser TEXT" +
                    ")";
                break;
            case "PostgreSQL":
                sql = "CREATE TABLE IF NOT EXISTS shelf (" +
                        "shelf_index SERIAL PRIMARY KEY," +
                        "obj_name TEXT," +
                        "obj_number INT," +
                        "obj_lastuptime TIMESTAMP," +
                        "lastuser TEXT" +
                    ")";
                break;
            case "SQLite":
                sql = "CREATE TABLE IF NOT EXISTS shelf (" +
                        "shelf_index INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "obj_name TEXT," +
                        "obj_number INTEGER," +
                        "obj_lastuptime DATETIME," +
                        "lastuser TEXT" +
                    ")";
                break;
            default:
                throw new UnsupportedOperationException("Unsupported DB type: " + dbType);
        }
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
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
    public void addDBCredentials(String dbHead, String url, String user, String password, String port) {
        Map<String,Map<String, String>> map = CfgIOutils.readjson("appconfig.cfg");

        if (map == null) map = new HashMap<>();

        Map<String, String> dbconfig = map.get("dbconfig");

        if (dbconfig == null) dbconfig = new HashMap<>();

        dbconfig.put("dbtype", dbHead);
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


    public List<Object[]> queryCustomShelfTable(String sql) {
        List<Object[]> result = new ArrayList<>();
        try {
            if (connection == null || connection.isClosed()) {
                connection = getConnection();
            }
            try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                int columnCount = rs.getMetaData().getColumnCount();
                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        row[i] = rs.getObject(i + 1);
                    }
                    result.add(row);
                }
            }
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        return result;
    }

    /**
     * 获取用户权限
     * @param username 待查询用户名
     * @return 用户权限: 1 用户, 2 管理员, 0 被ban
     */
    public int getUserPermission(String username) {
        String query = "SELECT state FROM staff WHERE username = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("state");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 设置用户权限
     * @param username 待设置用户名
     * @param state 用户权限
     * @return 是否成功
     */
    public boolean setUserPermission(String username, int state) {
        String sql = "UPDATE staff SET state = ? WHERE username = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, state);
            stmt.setString(2, username);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}