package laboratory.lab.shelf;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.db.DataBase;
import standard.GlobalVariables;

public class Shelf {
    
    private static DataBase dbUtils = DataBase.getInstance();
    
    /**
     * Get the total number of rows in the shelf table
     * @return Total rows, or -1 if the query fails
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
     * Get the total sum of obj_number in the shelf table
     * @return Total quantity, or -1 if the query fails
     */
    public static int getTotalItemQuantity() {
        String query = "SELECT SUM(obj_number) FROM shelf";
        try (PreparedStatement stmt = dbUtils.getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Error occurred while fetching total item quantity from shelf table.");
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Get the entry in the shelf table with obj_lastuptime closest to the current time
     * @return A string array containing lastuser and obj_lastuptime, or null if the query fails
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
        return null;
    }
}
