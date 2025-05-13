package database.db;

import database.error.*;
import java.sql.SQLException;

import local.utils.MiniOption;

public class errorHandler implements database.db.DBConnectionErrorHandler {
    @Override
    public void handleError(DBConnectError error) {
        // Handle the connection error here
        System.err.println("Database connection error: " + error.getMessage());
        new MiniOption("Database connection error", "Please check your database connection settings.", MiniOption.ERROR_MESSAGE);

        // You can also log the error or take other actions as needed
    }

    @Override
    public void handleError(DBStatusError error) {
        // Handle the status error here
        System.err.println("Database status error: " + error.getMessage());
        new MiniOption("Database status error", "Please check your database status.", MiniOption.ERROR_MESSAGE);

        // You can also log the error or take other actions as needed
    }

    @Override
    public void handleError(SQLException error) {
        // Handle the SQL exception here
        System.err.println("SQL error: " + error.getMessage());
        new MiniOption("SQL error", "Please check your SQL syntax.", MiniOption.ERROR_MESSAGE);
        // You can also log the error or take other actions as needed
    }
    /**
     * 处理其他异常
     * @param error 传入其他异常
     */
    @Override
    public void handleOtherError(Exception error) {
        // Handle other exceptions here
        System.err.println("Unknown error: " + error.getMessage());
        new MiniOption("Unknown error", "Unknown Error, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
        // You can also log the error or take other actions as needed
    }
}
