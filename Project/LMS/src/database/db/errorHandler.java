package database.db;

import database.error.*;


public class errorHandler implements database.DBConnectionErrorHandler {
    @Override
    public void handleConnectionError(DBConnectError error) {
        // Handle the connection error here
        System.err.println("Database connection error: " + error.getMessage());
        // You can also log the error or take other actions as needed
    }
    
}
