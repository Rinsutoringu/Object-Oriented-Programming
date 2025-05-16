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
    public static String dbUrl = "192.168.101.103";
    public static String dbType = "jdbc:mysql://";
    public static String dbName = "LMS_sql";
    public static String dbUser = "root";
    public static String dbPassword = "WPR_2333";
    public static String dbPort = "3306";

    // 这里存储了全局配色方案
    public static final Color getDBConLogic = new Color(244, 245, 246);

    public static String currentUsr = "TestName-RinChord";

    private GlobalVariables() {
        dbUrl = "192.168.101.103";
        dbType = "jdbc:mysql://";
        dbName = "LMS_sql";
        dbUser = "root";
        dbPassword = "WPR_2333";
        dbPort = "3306";
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

    public static void setDBType(String dbType) {
        GlobalVariables.dbType = dbType;
    }

    public static void setDBName(String dbName) {
        GlobalVariables.dbName = dbName;
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
    
    public static String getDBConnAddress() {
        return GlobalVariables.dbType + GlobalVariables.dbUrl + ":" + GlobalVariables.dbPort + "/" + GlobalVariables.dbName;
    }

    public static String getDBUser() {
        return GlobalVariables.dbUser;
    }

    public static String getDBPassword() {
        return GlobalVariables.dbPassword;
    }


    public static void setUserName(String userName) {
        GlobalVariables.currentUsr = userName;
    }

    public static String getUserName() {
        return GlobalVariables.currentUsr;
    }

    public static Color cgetDBConLogic() {
        return getDBConLogic;
    }

}

