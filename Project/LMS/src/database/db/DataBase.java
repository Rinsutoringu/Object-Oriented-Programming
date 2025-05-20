package database.db;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import database.error.DBConnectError;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.utils.CfgIOutils;
import standard.GlobalVariables;

public class DataBase {

    private Connection connection;
    private errorHandler eh = errorHandler.getInstance();
    private boolean connectionErrorShown = false;
    private int retryCount= 0;
    private static final int MAX_RETRIES = 3;

    private static DataBase instance;
    private DataBase() {
        GlobalVariables.getInstance();
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public void ResetRetryCount() {
        retryCount = 0;
    }


    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {

                if (retryCount >= MAX_RETRIES) {
                    return null;
                }

                connection = createConnect();
                
                retryCount++;
            }

            if (connection != null && !connection.isClosed()) {
                if (!isTableExists("staff")) {
                        createStaffTableIfNotExists();
                }
                if (!isTableExists("shelf")) {
                    createShelfTableIfNotExists();
                }
                retryCount = 0; 
            }

            return connection;
        } catch (Exception e) {
            if (!connectionErrorShown) {
                CatchException.handle(e, eh);
            }
        }
        return null;
    }

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

        private String getConnectionUrl() {
            String dbType = GlobalVariables.getDBType();
            String url = GlobalVariables.getDBUrl();
            String port = GlobalVariables.getDBPort();
            String dbsubname = GlobalVariables.getDBSubName();

            switch (dbType) {
                case "MySQL":
                    return "jdbc:mysql://" + url + ":" + port + "/" + dbsubname + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                case "PostgreSQL":
                    return "jdbc:postgresql://" + url + ":" + port + "/" + dbsubname;
                case "SQLite":
                    File dbFile = new File(url);
                    String dbFilePath;
                    if (dbFile.isAbsolute()) {
                        dbFilePath = url;
                    } else {
                        String jarDir = System.getProperty("user.dir");
                        dbFilePath = jarDir + File.separator + url;
                    }
                    return "jdbc:sqlite:" + dbFilePath;
                default:
                    return "";
            }
        }

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
                        "username TEXT," +
                        "password TEXT," +
                        "regdate TEXT," +
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
                        "obj_lastuptime TEXT," +
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

    public ResultSet SearchDB(String query) throws SQLException, SQLTimeoutException, DBConnectError, Exception{
        try {
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

    public ResultSet queryRow(String tableName, Map<String, Object> whereMap) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(tableName);
        if (whereMap != null && !whereMap.isEmpty()) {
            sql.append(" WHERE ");
            int i = 0;
            for (Map.Entry<String, Object> entry : whereMap.entrySet()) {
                if (i++ > 0) sql.append(" AND ");
                sql.append(entry.getKey()).append("=?");
            }
        }
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        int idx = 1;
        for (Object value : whereMap.values()) {
            pstmt.setObject(idx++, value);
        }
        return pstmt.executeQuery();
    }

    public void updateRow(String tableName, Map<String, Object> whereMap, Map<String, Object> updateMap) throws Exception {
        if (whereMap == null || whereMap.isEmpty() || updateMap == null || updateMap.isEmpty()) {
            throw new IllegalArgumentException("whereMap and updateMap Can Not Be empty");
        }
        StringBuilder sql = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        int i = 0;
        for (Map.Entry<String, Object> entry : updateMap.entrySet()) {
            if (i++ > 0) sql.append(", ");
            sql.append(entry.getKey()).append("=?");
        }
        sql.append(" WHERE ");
        i = 0;
        for (Map.Entry<String, Object> entry : whereMap.entrySet()) {
            if (i++ > 0) sql.append(" AND ");
            sql.append(entry.getKey()).append("=?");
        }
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            int idx = 1;
            for (Object value : updateMap.values()) {
                pstmt.setObject(idx++, value);
            }
            for (Object value : whereMap.values()) {
                pstmt.setObject(idx++, value);
            }
            pstmt.executeUpdate();
        }
    }

    public void insertRow(String tableName, Map<String, Object> valueMap) throws Exception {
        if (valueMap == null || valueMap.isEmpty()) {
            throw new IllegalArgumentException("valueMap Can Not Be empty");
        }
        StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        StringBuilder placeholders = new StringBuilder();
        int i = 0;
        for (String key : valueMap.keySet()) {
            if (i++ > 0) {
                sql.append(", ");
                placeholders.append(", ");
            }
            sql.append(key);
            placeholders.append("?");
        }
        sql.append(") VALUES (").append(placeholders).append(")");
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            int idx = 1;
            for (Object value : valueMap.values()) {
                pstmt.setObject(idx++, value);
            }
            pstmt.executeUpdate();
        }
    }

    public void deleteRow(String tableName, Map<String, Object> whereMap) throws Exception {
        if (whereMap == null || whereMap.isEmpty()) {
            throw new IllegalArgumentException("whereMap Can Not Be empty");
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

    public void update_DBCredentials_From_Config() {
        try {
            Map<String, Map<String, String>> map = CfgIOutils.readjson("appconfig.cfg");

            if (map == null) return;

            Map<String, String> dbconfig = map.get("dbconfig");

            if (dbconfig == null) return;

            String url = dbconfig.get("dbaddr");
            String user = dbconfig.get("dbuser");
            String password = dbconfig.get("dbpassword");
            String port = dbconfig.get("dbport");
            String dbtype = dbconfig.get("dbtype");

            if ("SQLite".equalsIgnoreCase(dbtype)) {
                File dbFile = new File(url);
                if (!dbFile.isAbsolute()) {
                    String jarDir = System.getProperty("user.dir");
                    url = jarDir + File.separator + url;
                }
            }

            GlobalVariables.setDBUrl(url);
            GlobalVariables.setDBUser(user);
            GlobalVariables.setDBPassword(password);
            GlobalVariables.setDBPort(port);
            GlobalVariables.setDBType(dbtype);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public int getNumOfUsers() {
        Connection conn = getConnection();
        if (conn == null) {
            System.err.println("Database connection is not available.");
            return 0;
        }

        String query = "SELECT COUNT(*) AS user_count FROM staff";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("user_count");
            }
        } catch (SQLException e) {
            CatchException.handle(e, eh);
        }
        return 0;
    }

    public int getNumOfObjects() {
        Connection conn = getConnection();
        if (conn == null) {
            System.err.println("Database connection is not available.");
            return 0;
        }

        String query = "SELECT COUNT(*) AS object_count FROM shelf";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("object_count");
            }
        } catch (SQLException e) {
            CatchException.handle(e, eh);
        }
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