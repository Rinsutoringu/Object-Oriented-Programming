package laboratory.lab.shelf;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.db.DataBase;
import standard.GlobalVariables;

public class Shelf {
    
    private static DataBase dbUtils = DataBase.getInstance();
    
    /**
     * 获取 shelf 表的总行数
     * @return 总行数，如果查询失败返回 -1
     */
    public static int getItemQuantity() {
        String query = "SELECT COUNT(*) FROM shelf";
        try (PreparedStatement stmt = dbUtils.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    /**
     * 获取 shelf 表中所有 obj_number 的总和
     * @return 总物资量，如果查询失败返回 -1
     */
    public static int getTotalItemQuantity() {
        String query = "SELECT SUM(obj_number) FROM shelf";
        try (PreparedStatement stmt = dbUtils.getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1); // 返回总和
            }
        } catch (Exception e) {
            System.err.println("Error occurred while fetching total item quantity from shelf table.");
            e.printStackTrace();
        }
        return -1; // 查询失败返回 -1
    }

    /**
     * 获取 shelf 表中 obj_lastuptime 最接近当前时间的条目
     * @return 包含 lastuser 和 obj_lastuptime 的字符串数组，如果查询失败返回 null
     */
    public static String[] getClosestLastUpdate() {
        String dbType = GlobalVariables.getDBType();
        String sql;

        if ("SQLite".equalsIgnoreCase(dbType)) {
            sql = "SELECT lastuser, obj_lastuptime FROM shelf " +
                "ORDER BY ABS(julianday(obj_lastuptime) - julianday('now')) ASC LIMIT 1";
        } else if ("MySQL".equalsIgnoreCase(dbType)) {
            sql = "SELECT lastuser, obj_lastuptime FROM shelf " +
                "ORDER BY ABS(TIMESTAMPDIFF(SECOND, obj_lastuptime, NOW())) ASC LIMIT 1";
        } else {
            throw new UnsupportedOperationException("Unsupported DB type: " + dbType);
        }

        try (PreparedStatement stmt = dbUtils.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String lastUser = rs.getString("lastuser");
                String lastUpdateTime = rs.getString("obj_lastuptime");
                return new String[]{lastUser, lastUpdateTime};
            }
        } catch (Exception e) {
            System.err.println("Error occurred while fetching the closest last update from shelf table.");
            e.printStackTrace();
        }
        return null; // 查询失败返回 null
    }

    
}
