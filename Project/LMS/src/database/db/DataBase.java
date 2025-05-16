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
import java.util.HashMap;
import java.util.Map;

import database.error.DBConnectError;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.error.AuthFailed;
import local.error.UserInfoError;
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

    // TODO 这里有问题
    /**
     * 检查表格是否存在
     * @throws DBConnectError
     */
    public void checkTableExist() throws DBConnectError {
        try {
            this.getConnection();
            if (!tableExists("staff")) throw new DBConnectError("Table 'staff' does not exist");
            if (!tableExists("Shelf")) throw new DBConnectError("Table 'Shelf' does not exist");
        } catch (Exception e) {
            CatchException.handle(e, eh);
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
     * 登录
     * @param username 用户登录时传入的用户名
     * @param password 用户登录时传入的密码
     * @exception AuthFailed 用户认证失败
     * @exception UserInfoError 用户信息错误
     * @exception DBConnectError 数据库连接错误
     */
    public boolean Login(String usr, String pwd) throws AuthFailed, UserInfoError, DBConnectError {
        usr = usr.trim();
        pwd = pwd.trim();
        String query = "SELECT password FROM staff WHERE username = '" + usr + "'";
        try {
            Connection connection = this.getConnection();
            if (connection == null || connection.isClosed()) 
                throw new DBConnectError("Database connection is not available");
            // 查找这个用户名对应的数据库信息
            ResultSet rs = this.SearchDB(query);
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
        }
    }

    /**
     * 注册
     * @param username 用户注册时传入的用户名
     * @param password 用户注册时传入的密码
     * @exception UserInfoError 用户信息错误
     * @exception DBConnectError 数据库连接错误
     */
    public void Register(String usr, String pwd) throws UserInfoError, DBConnectError {
        usr = usr.trim();
        pwd = pwd.trim();
        // 将用户名和密码插入数据库，同时为index增加1
        // 获取当前时间
        String insertQuery = "INSERT INTO staff (username, password, regdate, state) VALUES (?, ?, NOW(), 1)";
        // String updateIndexQuery = "UPDATE staff SET staff_index = staff_index + 1 WHERE username = ?";
        try {

            // DEBUG
            // System.out.println("Executing query: " + insertQuery);
            // System.out.println("Username: " + usr + ", Password: " + pwd);

            // 创建预编译语句，
            PreparedStatement insertStatement = this.getConnection().prepareStatement(insertQuery);
            insertStatement.setString(1, usr);
            insertStatement.setString(2, pwd);
            // 执行插入操作
            insertStatement.executeUpdate();

        } catch (Exception e) {
            CatchException.handle(e, eh);
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
}