package database.errorhandle;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import database.error.*;
import local.error.*;

public interface DBConnectionErrorHandler {

    /**
     * 处理DBConnectError
     * @param error 传入DBConnectError
     */
    void handleError(DBConnectError error);

    /**
     * 处理DBStatusError
     * @param error 传入DBStatusError
     */
    void handleError(DBStatusError error);

    /**
     * 处理SQLException
     * @param error 传入SQLException
     */
    void handleError(SQLException error);

    void handleError(SQLTimeoutException error);

    void handleError(UserInfoError error);

    void handleError(AuthFailed error);

    /**
     * 未知异常通用处理逻辑
     * @param error 传入其他异常
     */
    void handleOtherError(Exception error);
}