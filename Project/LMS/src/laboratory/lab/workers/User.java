package laboratory.lab.workers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import database.error.DBConnectError;
import database.errorhandle.CatchException;
import local.error.AuthFailed;
import local.error.UserInfoError;
import local.ui.miniwindow.MiniOption;
import standard.GlobalVariables;
import database.errorhandle.errorHandler;
import database.db.DataBase;

public class User {

    private static errorHandler eh = errorHandler.getInstance();
    private static DataBase dbutils = DataBase.getInstance();

    public User() {
    }

    public static boolean Login(String username, String password) throws AuthFailed, UserInfoError, DBConnectError {
        username = username.trim();
        password = password.trim();
        String query = "SELECT password FROM staff WHERE username = ?";
        ResultSet rs = null;

        int attempts = 0;
        boolean success = false;

        while (attempts < 3 && !success) {
            try (Connection connection = dbutils.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {

                if (connection == null || connection.isClosed()) {
                    throw new DBConnectError("Database connection is not available");
                }

                stmt.setString(1, username);
                System.out.println("Executing SQL: " + query + " [username=" + username + "]");

                rs = stmt.executeQuery();

                if (rs == null || !rs.next()) {
                    throw new UserInfoError("User not exist");
                }

                if (!password.equals(rs.getString("password"))) {
                    throw new UserInfoError("Password not match");
                }

                System.out.println("Login successful for user: " + username);
                success = true;
                return true;

            } catch (Exception e) {
                attempts++;
                CatchException.handle(e, eh);
                if (attempts >= 3) {
                    throw new UserInfoError("Login failed after retrying.");
                }
                System.out.println("Retrying login... Attempt " + (attempts + 1));
            } finally {
                try {
                    if (rs != null) rs.close();
                } catch (Exception e) {
                    CatchException.handle(e, eh);
                }
            }
        }

        return false; // 如果所有尝试都失败，返回 false
    }

    public static void Register(String usr, String pwd) throws UserInfoError, DBConnectError {
        usr = usr.trim();
        pwd = pwd.trim();
        Map<String, Object> map = new HashMap<>();
        map.put("username", usr);
        map.put("password", pwd);
        map.put("regdate", new java.sql.Timestamp(System.currentTimeMillis()));
        map.put("state", 1);

        boolean success = false;
        int attempts = 0;

        while (!success && attempts < 3) {
            try {
                dbutils.insertRow(GlobalVariables.getStaffTableName(), map);
                success = true;
            } catch (Exception e) {
                attempts++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    throw new UserInfoError("Registration interrupted.");
                }
                CatchException.handle(e, eh);
                if (attempts >= 3) {
                    throw new UserInfoError("Registration failed after retrying.");
                }
            }
        }
    }

    public static boolean userExists(String username) {
        String query = "SELECT COUNT(*) FROM staff WHERE username = ?";
        try (PreparedStatement stmt = dbutils.getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        return false;
    }

    public static boolean isAdmin(String name) {

        if (name.equals("root")) {
            return true;
        }
        
        if (!userExists(name)) {
            new MiniOption("User Not Found", "Cannot find this user, Please check.", MiniOption.WARNING_MESSAGE);
            return false;
        }
        if (dbutils.getUserPermission(name) == 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBanned(String name) {
        if (name.equals("root")) {
            return false;
        }
        if (!userExists(name)) {
            new MiniOption("User Not Found", "Cannot find this user, Please check.", MiniOption.WARNING_MESSAGE);
            return false;
        }
        if (dbutils.getUserPermission(name) == 0) {
            return true;
        } else {
            return false;
        }
    }
}
