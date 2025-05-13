package database.errorhandle;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import database.error.DBConnectError;
import database.error.DBStatusError;
import local.error.AuthFailed;
import local.error.UserInfoError;
import local.utils.MiniOption;

/**
 * 数据库连接错误处理类
 * 这里实现了标准的数据库连接错误处理接口
 * 实际的错误处理逻辑定义在了CatchException类中
 * 这里的逻辑仅起定义作用
 */
public class errorHandler implements database.errorhandle.DBConnectionErrorHandler {

    @Override
    public void handleError(DBConnectError error) {
        // Handle the connection error here
        System.err.println("Database connection error: " + error.getMessage());
        new MiniOption("Database connection error", "Please check your database connection settings, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }

    @Override
    public void handleError(DBStatusError error) {
        // Handle the status error here
        System.err.println("Database status error: " + error.getMessage());
        new MiniOption("Database status error", "Please check your database status, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);

    }

    @Override
    public void handleError(SQLException error) {
        // Handle the SQL exception here
        System.err.println("SQL error: " + error.getMessage());
        new MiniOption("SQL error", "Please check your SQL syntax, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }

    @Override
    public void handleError(SQLTimeoutException error) {
        // Handle SQL timeout exception here
        System.err.println("SQL timeout error: " + error.getMessage());
        new MiniOption("SQL timeout error", "Database connection timed out, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }
    @Override
    public void handleError(UserInfoError error) {
        // Handle user information error here
        System.err.println("User information error: " + error.getMessage());
        new MiniOption("User information error", "Database user information error, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }
    @Override
    public void handleError(AuthFailed error) {
        // Handle user information error here
        System.err.println("User information error: " + error.getMessage());
        new MiniOption("User information error", "Database user information error, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }

    @Override
    public void handleOtherError(Exception error) {
        // Handle other exceptions here
        System.err.println("Unknown error: " + error.getMessage());
        new MiniOption("Unknown error", "Unknown Error, Info: "+error.getMessage(), MiniOption.ERROR_MESSAGE);
    }

    public void handleError(Exception e, errorHandler eh) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleError'");
    }


}
