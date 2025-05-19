package laboratory.lab.shelf;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.db.DataBase;

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
    
}
