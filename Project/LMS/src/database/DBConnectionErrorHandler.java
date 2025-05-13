package database;

import database.error.DBConnectError;

public interface DBConnectionErrorHandler {
    void handleConnectionError(DBConnectError error);
}