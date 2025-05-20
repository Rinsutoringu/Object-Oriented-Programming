package laboratory.lab.workers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;

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

        if (username.equals("root")) {
            if (password.equals("passwd")) {
                System.out.println("Login successful for root user.");
                return true;
            } else {
                throw new UserInfoError("Invalid password for root user.");
            }
        }
        String query = "SELECT password, state FROM staff WHERE username = ?";
        ResultSet rs = null;

        try (Connection connection = dbutils.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            if (connection == null || connection.isClosed()) {
                throw new DBConnectError("Database connection is not available");
            }

            stmt.setString(1, username);
            System.out.println("Executing SQL: " + query + " [username=" + username + "]");

            rs = stmt.executeQuery();

            if (rs == null || !rs.next()) {
                throw new UserInfoError("User does not exist.");
            }

            if (!password.equals(rs.getString("password"))) {
                throw new UserInfoError("Invalid username or password.");
            }

            int state = rs.getInt("state");
            if (state == 0) {
                throw new UserInfoError("User is banned.");
            }

            System.out.println("Login successful for user: " + username);
            return true;

        } catch (Exception e) {
            CatchException.handle(e, eh);

        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                CatchException.handle(e, eh);
            }
        }
        return false;
    }

    public static void Register(String usr, String pwd, String repwd, JCheckBox checkBox) throws UserInfoError, DBConnectError {
        if (!checkBox.isSelected()) {
            throw new UserInfoError("Please read and accept the terms and conditions.");
        }

        usr = usr.trim();
        pwd = pwd.trim();
        repwd = repwd.trim();

        if (!pwd.equals(repwd)) {
            throw new UserInfoError("The two passwords do not match.");
        }

        if (userExists(usr)) {
            throw new UserInfoError("The username is already taken.");
        }

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
                    Thread.sleep(300);
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
