package database.error;

public class DBConnectError extends RuntimeException{
    public DBConnectError(String message) {
        super(message);
    }
    public DBConnectError(String message, Throwable cause) {
        super(message, cause);
    }
}
