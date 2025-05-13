package database.error;

import database.db.DataBaseUtils;

public class DBStatusError extends RuntimeException {
    public DBStatusError(String message) {
        
        super(message);
    }
    public DBStatusError(String message, Throwable cause) {
        super(message, cause);
    }
    
}
