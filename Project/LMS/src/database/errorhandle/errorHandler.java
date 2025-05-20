package database.errorhandle;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import com.mysql.cj.exceptions.CJException;

import database.error.DBConnectError;
import database.error.DBStatusError;
import local.error.AuthFailed;
import local.error.UserInfoError;

public class errorHandler implements database.errorhandle.DBConnectionErrorHandler, standard.StandardUTIL {
    private static errorHandler instance;
    private errorHandler() {
    }
    public static errorHandler getInstance() {
        if (instance == null) {
            instance = new errorHandler();
        }
        return instance;
    }

    @Override
    public void handleError(DBConnectError error) {
        // System.err.println("Database connection error: " + error.getMessage());
        // new MiniOption("Database connection error", "Please check your database connection settings, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }

    @Override
    public void handleError(DBStatusError error) {
        // System.err.println("Database status error: " + error.getMessage());
        // new MiniOption("Database status error", "Please check your database status, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }

    @Override
    public void handleError(SQLException error) {
        // System.err.println("SQL error: " + error.getMessage());
        // new MiniOption("SQL error", "Please check your SQL syntax, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }

    @Override
    public void handleError(SQLTimeoutException error) {
        // System.err.println("SQL timeout error: " + error.getMessage());
        // new MiniOption("SQL timeout error", "Database connection timed out, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }
    @Override
    public void handleError(UserInfoError error) {
        // System.err.println("User information error: " + error.getMessage());
        // new MiniOption("User information error", "Database user information error, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }
    @Override
    public void handleError(AuthFailed error) {
        // System.err.println("User information error: " + error.getMessage());
        // new MiniOption("User information error", "Database user information error, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }
    @Override
    public void handleError(CJException error) {
        // System.err.println("User information error: " + error.getMessage());
        // new MiniOption("User information error", "Database user information error, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }
    @Override
    public void handleError(NullPointerException error) {
        // System.err.println("User information error: " + error.getMessage());
        // new MiniOption("User information error", "Database user information error, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }

    @Override
    public void handleOtherError(Exception error) {
        // System.err.println("Unknown error: " + error.getMessage());
        // new MiniOption("Unknown error", "Unknown Error, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }
    public void handleError(String string, Exception ex) {
        // throw new UnsupportedOperationException("Unimplemented method 'handleError'");
    }
}
