package standard;

import java.awt.Color;

/**
 * 实现从指定目录读取配置文件，本地也保持一个默认值
 * 可选：设置json文件/本地默认值/UI上可视化设置
 */
public class GlobalVariables {

    private static GlobalVariables instance;

    // 数据库连接信息存储在这里
    // public static String dbfullUrl = "jdbc:mysql://192.168.101.103:3306/LMS_sql";


    // 数据库地址
    public static String dbUrl;
    // 数据库端口
    public static String dbPort;
    // 数据库类型
    public static String dbType;
    // 数据库子名称
    public static String dbSubName;
    // 数据库账号名
    public static String dbUser;
    // 数据库密码
    public static String dbPassword;

    // 表格名
    public static String staffTableName;
    public static String shelfTableName;

    // 这里存储了全局配色方案
    public static final Color getDBConLogic = new Color(244, 245, 246);

    public static final Color registerLogic = new Color(244, 245, 246);

    public static final Color sideBarLogic = new Color(244, 245, 246);

    public static String currentUsr;

    private GlobalVariables() {
        setDBUrl("192.168.101.103");
        setDBPort("56003");
        setDBType("MySQL");
        setDBSubName("LMS_sql");
        setUserName("root");
        setDBPassword("WPR_2333");
        setStaffTableName("staff");
        setShelfTableName("shelf");

        // DEBUG
        setUserName("TestName-RinChord");
        // 私有构造函数，防止外部实例化
    }

    public static GlobalVariables getInstance() {
        if (instance == null) {
            instance = new GlobalVariables();
        }
        return instance;
    }

    public static void setDBUrl(String dbUrl) {
        GlobalVariables.dbUrl = dbUrl;
    }

    public static String getDBUrl() {
        return dbUrl;
    }

    public static void setDBSubName(String dbSubName) {
        GlobalVariables.dbSubName = dbSubName;
    }

    public static String getDBSubName() {
        return dbSubName;
    }

    public static void setDBType(String dbType) {
        GlobalVariables.dbType = dbType;
    }

    public static String getDBType() {
        return dbType;
    }

    public static void setDBUser(String dbUser) {
        GlobalVariables.dbUser = dbUser;
    }

    public static void setDBPassword(String dbPassword) {
        GlobalVariables.dbPassword = dbPassword;
    }

    public static void setDBPort(String dbPort) {
        GlobalVariables.dbPort = dbPort;
    }

    public static String getDBPort() {
        return dbPort;
    }

    public static String getDBUser() {
        return dbUser;
    }

    public static String getDBPassword() {
        return dbPassword;
    }


    public static void setUserName(String userName) {
        currentUsr = userName;
    }

    public static String getUserName() {
        return currentUsr;
    }

    public static Color cgetDBConLogic() {
        return getDBConLogic;
    }

    public static Color cgetRegisterLogic() {
        return registerLogic;
    }

    public static Color cgetSideBarLogic() {
        return sideBarLogic;
    }

    public static void setShelfTableName(String shelfTableName) {
        GlobalVariables.shelfTableName = shelfTableName;
    }

    public static String getShelfTableName() {
        return shelfTableName;
    }

    public static void setStaffTableName(String staffTableName) {
        GlobalVariables.staffTableName = staffTableName;
    }

    public static String getStaffTableName() {
        return staffTableName;
    }
}

