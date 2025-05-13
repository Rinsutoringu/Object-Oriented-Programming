package database.errorhandle;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import database.error.*;
import local.error.*;

public class CatchException {
    public static void handle(Exception e, errorHandler eh) {
        // 根据异常类型调用相应的处理方法
        if (e instanceof DBConnectError) {
            eh.handleError((DBConnectError) e);

        } else if (e instanceof DBStatusError) {
            eh.handleError((DBStatusError) e);

        } else if (e instanceof SQLException) {
            eh.handleError((SQLException) e);
            
        } else if (e instanceof SQLTimeoutException) {
            eh.handleError((SQLTimeoutException) e);

        } else if (e instanceof UserInfoError) {
            eh.handleError((UserInfoError) e);

        } else if (e instanceof AuthFailed) {
            eh.handleError((AuthFailed) e);

        } else {
            eh.handleOtherError(e);
        }
    }
}
